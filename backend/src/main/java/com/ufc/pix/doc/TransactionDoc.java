package com.ufc.pix.doc;


import com.ufc.pix.dto.*;
import com.ufc.pix.enumeration.TransactionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionDoc {

    @Operation(summary = "Create a transaction by ID", description = "Create a transaction using a sender and receiver ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/id")
    ResponseEntity<Void> createById(@RequestBody @Valid CreateTransactionByIdDto transactionDto);

    @Operation(summary = "Create a transaction by PIX", description = "Create a transaction using PIX key.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/pix/{userSenderId}")
    ResponseEntity<Void> createByPix(
            @RequestBody @Valid CreateTransactionByPixDto transactionDto,
            @PathVariable UUID userSenderId
    );

    @Operation(summary = "Cancel a transaction", description = "Cancel a pending transaction by transaction ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Transaction cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/pix/cancel/{transactionId}")
    ResponseEntity<Void> cancelByTransactionId(@PathVariable UUID transactionId);

    @Operation(summary = "List transactions", description = "Retrieve a list of transactions based on filters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid filters", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    ResponseEntity<List<ViewTransactionDto>> list(
            @RequestParam(value = "id", required = false) UUID id,
            @RequestParam(value = "transferValueStart", required = false) Double transferValueStart,
            @RequestParam(value = "transferValueEnd", required = false) Double transferValueEnd,
            @RequestParam(value = "status", required = false) TransactionStatus status,
            @RequestParam(value = "createdAtStart", required = false) LocalDateTime createdAtStart,
            @RequestParam(value = "createdAtEnd", required = false) LocalDateTime createdAtEnd,
            @RequestParam(value = "finishedAtStart", required = false) LocalDateTime finishedAtStart,
            @RequestParam(value = "finishedAtEnd", required = false) LocalDateTime finishedAtEnd,
            @RequestParam(value = "sendDateStart", required = false) LocalDateTime sendDateStart,
            @RequestParam(value = "sendDateEnd", required = false) LocalDateTime sendDateEnd,
            @RequestParam(value = "senderId", required = false) UUID senderId,
            @RequestParam(value = "receiverId", required = false) UUID receiverId
    );
}
