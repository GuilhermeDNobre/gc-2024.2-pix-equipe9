package com.ufc.pix.controller;

import com.ufc.pix.dto.CreateAccountDto;
import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.ViewAccountDto;
import com.ufc.pix.model.Account;
import com.ufc.pix.model.Transaction;
import com.ufc.pix.repository.TransactionRepository;
import com.ufc.pix.service.impl.AccountService;
import com.ufc.pix.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    TransactionRepository statementRepository;

    @PostMapping("/accounts")
    public ResponseEntity<Void> createAccount(@RequestBody @Valid CreateAccountDto accountDTO) {
        this.accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody @Valid CreateTransactionByIdDto statementDto){
        var statement = new Transaction();
        BeanUtils.copyProperties(statementDto, statement);
        Account account = accountRepository.findAccountsById(statementDto.getSender_id());
        Account accountReceiver = accountRepository.findAccountsById(statementDto.getReceiver_id());
        if(account.getBalance() < statementDto.getValue()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor é menor que o saldo em conta");
        }
        statementRepository.save(statement);   
        var newBalanceSender = account.getBalance() - statementDto.getValue();
        var newBalanceReceiver = accountReceiver.getBalance() + statementDto.getValue();
        account.setBalance(newBalanceSender);
        accountRepository.save(account);
        accountReceiver.setBalance(newBalanceReceiver);
        accountRepository.save(accountReceiver);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sua transferência foi concluída e seu novo saldo é de: " + newBalanceSender);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<ViewAccountDto>> getAllAccounts(){
        List<Account> list =accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(list.stream().map(Account::toView).toList());
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
    public ResponseEntity<Object> updateAccount(@PathVariable UUID id, @RequestBody @Valid CreateAccountDto accountDTO) {
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
