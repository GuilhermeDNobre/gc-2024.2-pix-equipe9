package com.ufc.pix.controller;

import com.ufc.pix.doc.TransactionDoc;
import com.ufc.pix.dto.*;
import com.ufc.pix.enumeration.TransactionStatus;
import com.ufc.pix.service.TransactionService;
import com.ufc.pix.service.impl.TokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController implements TransactionDoc {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TokenServiceImpl tokenService;

    @PostMapping("/id")
    @CrossOrigin
    public ResponseEntity<Void> createById(@RequestBody @Valid CreateTransactionByIdDto transactionDto) {
        this.transactionService.createById(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/pix/{userSenderId}")
    @CrossOrigin
    public ResponseEntity<Void> createByPix(
            @RequestBody @Valid CreateTransactionByPixDto transactionDto,
            @PathVariable UUID userSenderId
    ) {
        this.transactionService.createByPix(userSenderId, transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/pix/cancel/{transactionId}")
    public ResponseEntity<Void> cancelByTransactionId(@PathVariable UUID transactionId) {
        this.transactionService.cancelPendingTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<ViewTransactionDto>> list(
            @RequestParam(value = "id",required = false)            UUID id,
            @RequestParam(value = "transferValueStart",required = false) Double transferValueStart,
            @RequestParam(value = "transferValueEnd",required = false) Double transferValueEnd,
            @RequestParam(value = "status",required = false) TransactionStatus status,
            @RequestParam(value = "createdAtStart",required = false) LocalDateTime createdAtStart,
            @RequestParam(value = "createdAtEnd",required = false)  LocalDateTime createdAtEnd,
            @RequestParam(value = "finishedAtStart",required = false) LocalDateTime finishedAtStart,
            @RequestParam(value = "finishedAtEnd",required = false) LocalDateTime finishedAtEnd,
            @RequestParam(value = "sendDateStart",required = false) LocalDateTime sendDateStart,
            @RequestParam(value = "sendDateEnd",required = false)   LocalDateTime sendDateEnd,
            @RequestParam(value = "senderId",required = false)      UUID senderId,
            @RequestParam(value = "receiverId",required = false)    UUID receiverId
    ){
        var list = this.transactionService.list(new SearchTrasactionDto(
                id,
                transferValueStart,
                transferValueEnd,
                status,
                createdAtStart,
                createdAtEnd,
                finishedAtStart,
                finishedAtEnd,
                sendDateStart,
                sendDateEnd,
                senderId,
                receiverId
        ));
        return ResponseEntity.ok(list);
    }


}
