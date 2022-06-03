package com.test.app.exception;

public class WrongArgException extends RuntimeException {

    public WrongArgException(String message) {
        super(message);
    }

}
