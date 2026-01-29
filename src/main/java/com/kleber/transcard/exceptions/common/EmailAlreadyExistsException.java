package com.kleber.transcard.exceptions.common;

import com.kleber.transcard.exceptions.BaseException;

public class EmailAlreadyExistsException extends BaseException {
    public EmailAlreadyExistsException() {
        super("Email já cadastrado");
    }
}