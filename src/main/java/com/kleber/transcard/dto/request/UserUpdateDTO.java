package com.kleber.transcard.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


public record UserUpdateDTO(

        @Schema(description = "Nome do usuário", example = "Teste")
        String name,

        @Schema(description = "Email do usuário", example = "teste@email.com")
        String email,

        @Schema(description = "Senha do usuário", example = "123456")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) {}
