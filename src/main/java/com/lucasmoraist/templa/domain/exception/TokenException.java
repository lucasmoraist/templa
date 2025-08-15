package com.lucasmoraist.templa.domain.exception;

public class TokenException extends RuntimeException {
    public TokenException(String message, Throwable ex) {
        super(message, ex);
    }
}
