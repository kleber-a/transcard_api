package com.kleber.transcard.exceptions.auth;

import com.kleber.transcard.exceptions.BaseException;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super("Acesso não autorizado");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}