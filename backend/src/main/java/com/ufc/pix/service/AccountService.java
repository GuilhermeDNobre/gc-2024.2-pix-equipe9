package com.ufc.pix.service;

import com.ufc.pix.dto.AccountDTO;
import com.ufc.pix.model.Account;
import com.ufc.pix.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(AccountDTO accountDTO) {
        var account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getOneAccount(UUID id) {
        return Optional.ofNullable(accountRepository.findAccountsById(id));
    }

    public Optional<Account> updateAccount(UUID id, AccountDTO accountDTO) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findAccountsById(id));
        if (accountOptional.isPresent()) {
            var account = accountOptional.get();
            BeanUtils.copyProperties(accountDTO, account);
            return Optional.of(accountRepository.save(account));
        }
        return Optional.empty();
    }

    public boolean deleteAccount(UUID id) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findAccountsById(id));
        if (accountOptional.isPresent()) {
            accountRepository.delete(accountOptional.get());
            return true;
        }
        return false;
    }
}