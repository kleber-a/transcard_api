package com.kleber.transcard.exceptions.auth;

import com.kleber.transcard.exceptions.BaseException;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException() {
        super("Email ou senha inválidos");
    }
}