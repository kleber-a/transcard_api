package com.kleber.transcard.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


public record UserUpdateDTO(

        @Schema(description = "Nome do usuário", example = "Teste")
        String name,

        @Schema(description = "Email do usuário", example = "teste@email.com")
        String email,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) {}


//public class UserUpdateDTO {
//
//    @Schema(description = "Nome do usuário", example = "Teste")
//    private String name;
//
//    @Schema(description = "Email do usuário", example = "teste@email.com")
//    private String email;
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String password;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
