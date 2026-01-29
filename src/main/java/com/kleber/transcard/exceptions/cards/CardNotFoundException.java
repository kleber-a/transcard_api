package com.kleber.transcard.exceptions.cards;

import com.kleber.transcard.exceptions.BaseException;

public class CardNotFoundException extends BaseException {
    public CardNotFoundException() {
        super("Cartão não encontrado");
    }
}