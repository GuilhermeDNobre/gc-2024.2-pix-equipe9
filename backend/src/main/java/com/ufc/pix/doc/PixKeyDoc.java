package com.ufc.pix.doc;

import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface PixKeyDoc {

    @Operation(summary = "Generate a random PIX key", description = "Generates a random PIX key for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PIX key generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/generate/{userId}")
    ResponseEntity<Void> generateRandomPixKey(@PathVariable UUID userId);

    @Operation(summary = "Register a PIX key", description = "Registers a PIX key for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PIX key registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "Random key type not allowed in this request", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/register/{userId}")
    ResponseEntity<Void> registerPixKey(
            @PathVariable UUID userId,
            @RequestParam KeyType type,
            @RequestParam(required = false) String key
    );

    @Operation(summary = "Validate a PIX key", description = "Validates a given PIX key.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PIX key validated successfully"),
            @ApiResponse(responseCode = "404", description = "PIX key not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/validate")
    ResponseEntity<ViewPixKeyDto> validatePixKey(@RequestParam String key);

    @Operation(summary = "Get all PIX keys", description = "Retrieves a list of all registered PIX keys.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PIX keys retrieved successfully")
    })
    @GetMapping("")
    ResponseEntity<List<ViewPixKeyDto>> getAll();
}
