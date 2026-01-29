package com.kleber.transcard.exceptions.cards;

import com.kleber.transcard.exceptions.BaseException;

public class CardAlreadyExistsException extends BaseException {
    public CardAlreadyExistsException() {
        super("Cartão já cadastrado");
    }
}