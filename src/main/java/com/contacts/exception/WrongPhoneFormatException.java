package com.contacts.exception;

public class WrongPhoneFormatException extends Throwable {
    public WrongPhoneFormatException(String message) {
        super(message);
    }
}
