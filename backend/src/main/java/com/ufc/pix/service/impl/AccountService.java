package com.ufc.pix.service.impl;

import com.ufc.pix.dto.CreateAccountDto;
import com.ufc.pix.dto.UpdateAccountDto;
import com.ufc.pix.dto.ViewAccountDto;
import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.Account;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createAccount(CreateAccountDto accountDTO) {
        var account = this.accountRepository.findByAgencyAndNumber(accountDTO.getAgency(),accountDTO.getNumber());

        if (account.isPresent()) throw new BusinessException("There is already an account with that number and agency");

        //verifica se o usuario existe
        var user = this.userRepository.findById(accountDTO.getUserId()).orElseThrow(
                () -> new BusinessException("User not found",HttpStatus.NOT_FOUND)
        );
        //verifica se o usuario esta ativo ou foi deletado
        if (user.getStatus() != UserStatus.ACTIVE){
            throw new BusinessException("User is not active");
        }

        //verifica se esse usuario ja tem conta
        if (user.getAccount() != null){
            throw new BusinessException("User already has an account");
        }

        Account accountToSave = new Account();
        accountToSave.setUser(user);
        accountToSave.setAgency(accountDTO.getAgency());
        accountToSave.setBalance(accountDTO.getBalance());
        accountToSave.setType(accountDTO.getType());
        accountToSave.setNumber(accountDTO.getNumber());
        accountToSave.setPixKeys(null);
        accountToSave.setStatus(AccountStatus.ACTIVE);
        accountToSave.setPassword(passwordEncoder.encode(accountDTO.getPassword())); //adicione senha com hash

        var savedAccount = accountRepository.save(accountToSave);
        user.setAccount(savedAccount);
        userRepository.save(user);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getOneAccount(UUID id) {
        return Optional.ofNullable(accountRepository.findAccountsById(id));
    }

    public void updateAccount(UUID id, UpdateAccountDto dto) {
        var account = this.accountRepository.findById(id).orElseThrow(()->new BusinessException("Account not found",HttpStatus.NOT_FOUND));

        if (dto.getAgency() != null) account.setAgency(dto.getAgency());
        if (dto.getType() != null) account.setAgency(dto.getAgency());
        if (dto.getPassword() != null) account.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getNumber() != null) account.setNumber(dto.getNumber());

        this.accountRepository.save(account);

    }

    public void deleteAccount(UUID id) {
        var account = this.accountRepository.findById(id).orElseThrow(()->new BusinessException("Account not found",HttpStatus.NOT_FOUND));
        account.setStatus(AccountStatus.DELETED);
        this.accountRepository.save(account);
    }

    public void blockAccount(UUID id) {
        var account = this.accountRepository.findById(id).orElseThrow(()->new BusinessException("Account not found",HttpStatus.NOT_FOUND));
        account.setStatus(AccountStatus.BLOCKED);
        this.accountRepository.save(account);
    }

    public ViewAccountDto findById(UUID id) {
        return this.accountRepository.findById(id).orElseThrow(
                ()-> new BusinessException("Account not found", HttpStatus.NOT_FOUND)).toView();
    }
}