package com.kleber.transcard.dto.request;

import com.kleber.transcard.entity.enums.CardType;

public record CreateCardDTO(
        Long cardNumber,
        String cardName,
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
