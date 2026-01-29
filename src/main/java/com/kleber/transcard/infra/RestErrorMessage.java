package com.kleber.transcard.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

public class RestErrorMessage {

    @Schema(description = "Status HTTP", example = "400 BAD_REQUEST")
    private HttpStatus status;

    @Schema(description = "Mensagem de erro", example = "Email já cadastrado")
    private String message;

    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
