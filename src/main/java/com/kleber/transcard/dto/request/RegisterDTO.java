package com.kleber.transcard.dto.request;

import com.kleber.transcard.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterDTO(
        @Schema(description = "Nome completo do usuário", example = "Joao da Silva")
        String name,
        @Schema(description = "Email do usuário", example = "joao@email.com")
        String email,
        @Schema(description = "Senha do usuário", example = "joao12345")
        String password,
        @Schema(description = "Tipo do usuário", example = "ADMIN", allowableValues = {"ADMIN", "USER"})
        Role role
) {
}
