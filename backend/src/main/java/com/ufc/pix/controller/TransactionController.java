package com.ufc.pix.controller;

import com.ufc.pix.dto.*;
import com.ufc.pix.model.Account;
import com.ufc.pix.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Void> create(@RequestBody @Valid CreateTransactionByIdDto transactionDto) {
        this.transactionService.create(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<ViewTransactionDto>> list(
            @RequestParam(value = "id", required = false) UUID id,
            @RequestParam(value = "valueStart", required = false) Double valueStart,
            @RequestParam(value = "valueEnd", required = false) Double valueEnd,
            @RequestParam(value = "transactionDateStart", required = false) LocalDateTime transactionDateStart,
            @RequestParam(value = "transactionDateEnd", required = false) LocalDateTime transactionDateEnd,
            @RequestParam(value = "senderId", required = false) UUID senderId,
            @RequestParam(value = "receiverId", required = false) UUID receiverId
    ){
        var list = this.transactionService.list(new SearchTrasactionDto(id,valueStart,valueEnd,transactionDateStart,transactionDateEnd,senderId,receiverId));
        return ResponseEntity.ok(list);
    }

}
