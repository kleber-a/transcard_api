package com.kleber.transcard.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthDTO(

        @Schema(description = "Email do usuário", example = "exemplo@email.com")
        String email,

        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {
}
