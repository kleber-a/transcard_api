package com.kleber.transcard.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponseDTO(
        @Schema(description = "Token JWT gerado após login", example = "12ffkfd")
        String token,

        @Schema(description = "Informações do usuário")
        UserDTO user
) {
}
