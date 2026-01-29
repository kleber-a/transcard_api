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


//public class CreateUserDTO {
//
//    @Schema(description = "Nome do usuário", example = "Joao da Silva")
//    private String name;
//
//    @Schema(description = "Email do usuário", example = "exemplo@email.com")
//    private String email;
//
//    @Schema(description = "Senha do usuário", example = "exemplo@email.com")
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
