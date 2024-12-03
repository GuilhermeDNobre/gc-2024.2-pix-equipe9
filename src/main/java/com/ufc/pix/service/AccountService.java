package com.ufc.pix.service;

import com.ufc.pix.dto.AccountDTO;
import com.ufc.pix.model.Account;
import com.ufc.pix.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Account> getOneAccount(Integer agency, Integer number) {
        return Optional.ofNullable(accountRepository.findByAccountAgencyAndAccountNumber(agency, number));
    }

    public Optional<Account> updateAccount(Integer agency, Integer number, AccountDTO accountDTO) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findByAccountAgencyAndAccountNumber(agency, number));
        if (accountOptional.isPresent()) {
            var account = accountOptional.get();
            BeanUtils.copyProperties(accountDTO, account);
            return Optional.of(accountRepository.save(account));
        }
        return Optional.empty();
    }

    public boolean deleteAccount(Integer agency, Integer number) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findByAccountAgencyAndAccountNumber(agency, number));
        if (accountOptional.isPresent()) {
            accountRepository.delete(accountOptional.get());
            return true;
        }
        return false;
    }
}