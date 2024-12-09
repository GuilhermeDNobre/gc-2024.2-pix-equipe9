package com.ufc.pix.controller;

import com.ufc.pix.dto.AccountDTO;
import com.ufc.pix.dto.StatementDto;
import com.ufc.pix.model.Account;
import com.ufc.pix.model.Statement;
import com.ufc.pix.service.AccountService;
import com.ufc.pix.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        var account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountRepository.save(account));
    }



    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> list =accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Object> getOneAccount(@PathVariable(value = "id") UUID id) {
        Optional<Account> account0 = Optional.ofNullable(accountRepository.findAccountsById(id));
        if (account0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(account0.get());
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable UUID id, @RequestBody @Valid AccountDTO accountDTO) {
        Optional<Account> account0 = Optional.ofNullable(accountRepository.findAccountsById(id));
        if (account0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        var account = account0.get();
        BeanUtils.copyProperties(accountDTO, account);
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.save(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable UUID id) {
        Optional<Account> account0 = Optional.ofNullable(accountRepository.findAccountsById(id));
        if (account0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        accountRepository.delete(account0.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
