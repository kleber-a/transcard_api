package com.kleber.transcard.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserDTO(

        @Schema(description = "Nome do usuário", example = "Joao da Silva")
        String name,

        @Schema(description = "Email do usuário", example = "exemplo@email.com")
        String email,

        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {}
