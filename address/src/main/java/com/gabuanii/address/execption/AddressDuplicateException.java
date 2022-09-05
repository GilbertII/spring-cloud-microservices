package com.gabuanii.address.execption;

public class AddressDuplicateException extends RuntimeException {
    public AddressDuplicateException(String message) {
        super(message);
    }

    public AddressDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
