package com.ufc.pix.doc;

import com.ufc.pix.dto.*;
import com.ufc.pix.enumeration.UserAccess;
import com.ufc.pix.enumeration.UserStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import java.util.UUID;

@Tag(name = "User Controller", description = "Endpoints para gerenciamento de usuários")
public interface UserDoc {

    @Operation(
            summary = "Registrar um novo usuário",
            description = "Registra um novo usuário no sistema com as informações fornecidas.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário registrado com sucesso",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados fornecidos são inválidos",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<Void> create(@RequestBody @Valid CreateUserDto userDto);

    @Operation(
            summary = "Autenticar usuário",
            description = "Realiza autenticação de um usuário com as credenciais fornecidas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticação realizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Token.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de login inválidos",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais incorretas ou conta inativa",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<Token> login(@RequestBody @Valid LoginDto loginDto);

    @Operation(
            summary = "Obter informações de um usuário",
            description = "Obtém as informações de um usuário baseado no ID fornecido.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Informações do usuário retornadas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ViewUserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<ViewUserDto> getById(@Parameter(description = "ID do usuário", required = true) @RequestParam UUID userId);

    @Operation(
            summary = "Listar usuários por filtros",
            description = "Obtém as informações de usuários baseado em atributos fornecidos por parametro.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Informações do usuário retornadas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ViewUserDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<List<ViewUserDto>> list(
            UUID id,
            String name,
            String email,
            String cpf,
            LocalDate birthDate,
            UserStatus status,
            UserAccess access
    );

    @Operation(
            summary = "Atualizar informações de um usuário",
            description = "Atualiza as informações de um usuário existente com base no ID fornecido.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário atualizado com sucesso",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados fornecidos são inválidos",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<Void> update(
            @Parameter(description = "ID do usuário", required = true) @RequestParam UUID userId,
            @RequestBody @Valid UpdateUserDto userDto
    );

    @Operation(
            summary = "Excluir um usuário",
            description = "Exclui um usuário do sistema com base no ID fornecido.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário excluído com sucesso",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário", required = true) @RequestParam UUID userId
    );

    @Operation(
            summary = "Bloquear um usuário",
            description = "Bloqueia um usuário no sistema.",
            security = @SecurityRequirement(name = "Bearer")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário bloqueado com sucesso",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado",
                    content = @Content()
            )
    })
    ResponseEntity<Void> block(@Parameter(description = "ID do usuário a ser bloqueado", required = true) @RequestParam UUID userId);
}
