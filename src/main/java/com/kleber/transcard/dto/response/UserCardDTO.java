package com.kleber.transcard.dto.response;

import com.kleber.transcard.entity.enums.CardType;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserCardDTO {

    @Schema(description = "ID do cartão", example = "5")
    private Long id;

    @Schema(description = "Número do cartão", example = "121321123")
    private Long cardNumber;

    @Schema(description = "Nome do cartão", example = "Card3")
    private String CardName;

    @Schema(description = "Status do cartão", example = "true")
    private Boolean status = true;

    @Schema(description = "Tipo do cartão", example = "TRABALHADOR")
    private CardType cardType;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
