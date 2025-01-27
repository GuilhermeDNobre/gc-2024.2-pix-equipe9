package com.ufc.pix.doc;

import com.ufc.pix.dto.CreateAccountDto;
import com.ufc.pix.dto.UpdateAccountDto;
import com.ufc.pix.dto.ViewAccountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface AccountDoc {

    @Operation(summary = "Create a new account", description = "Registers a new account with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping()
    ResponseEntity<Void> createAccount(@RequestBody @Valid CreateAccountDto accountDTO);

    @Operation(summary = "Get all accounts", description = "Retrieves a list of all accounts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully")
    })
    @GetMapping()
    ResponseEntity<List<ViewAccountDto>> getAllAccounts();

    @Operation(summary = "Get account by ID", description = "Retrieves the details of a specific account by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/accounts/{id}")
    ResponseEntity<ViewAccountDto> getOneAccount(@PathVariable(value = "id") UUID id);

    @Operation(summary = "Update an account", description = "Updates the details of an existing account by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/accounts/{id}")
    ResponseEntity<Void> updateAccount(@PathVariable UUID id, @RequestBody @Valid UpdateAccountDto accountDTO);

    @Operation(summary = "Delete an account", description = "Deletes an account by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/accounts/{id}")
    ResponseEntity<Void> deleteAccount(@PathVariable UUID id);
}
