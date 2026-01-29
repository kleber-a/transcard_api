    package com.kleber.transcard.exceptions;

    public abstract class BaseException extends RuntimeException {
        protected BaseException(String message) {
            super(message);
        }
    }