package com.kleber.transcard.exceptions.common;

import com.kleber.transcard.exceptions.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}