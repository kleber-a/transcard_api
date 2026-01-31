package com.kleber.transcard.dto.request;

import com.kleber.transcard.entity.enums.CardType;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCardDTO(
        @Schema(description = "Número do cartão", example = "121212131")
        Long cardNumber,
        @Schema(description = "Nome do cartão", example = "Cartão...")
        String cardName,
        @Schema(description = "Tipo do Cartão", example = "TRABALHADOR")
        CardType cardType
) {}

//public class CreateCardDTO {
//    private Long cardNumber;
//    private String cardName;
//    private CardType cardType;
//
//
//    public Long getCardNumber() {
//        return cardNumber;
//    }
//
//    public void setCardNumber(Long cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public String getCardName() {
//        return cardName;
//    }
//
//    public void setCardName(String cardName) {
//        this.cardName = cardName;
//    }
//
//    public CardType getCardType() {
//        return cardType;
//    }
//
//    public void setCardType(CardType cardType) {
//        this.cardType = cardType;
//    }
//}
