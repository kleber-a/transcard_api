package com.kleber.transcard.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kleber.transcard.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class UserDTO {

    @Schema(description = "ID do usuário", example = "10")
    private Long id;

    @Schema(description = "Nome do usuário", example = "Teste")
    private String name;

    @Schema(description = "Email do usuário", example = "teste@email.com")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "Cartões do usuário")
    private List<UserCardDTO> cards;

    @Schema(description = "Role do usuário", example = "USER")
    private Role role;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserCardDTO> getCards() {
        return cards;
    }

    public void setCards(List<UserCardDTO> cards) {
        this.cards = cards;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
