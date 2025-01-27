package com.ufc.pix.controller;

import com.ufc.pix.doc.AccountDoc;
import com.ufc.pix.dto.CreateAccountDto;
import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.UpdateAccountDto;
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
@RequestMapping("accounts")
public class AccountController implements AccountDoc {
    @Autowired
    AccountService accountService;

    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody @Valid CreateAccountDto accountDTO) {
        this.accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<ViewAccountDto>> getAllAccounts() {
        List<Account> list = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(list.stream().map(Account::toView).toList());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<ViewAccountDto> getOneAccount(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(this.accountService.findById(id));
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable UUID id, @RequestBody @Valid UpdateAccountDto accountDTO) {
        this.accountService.updateAccount(id,accountDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        this.accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
