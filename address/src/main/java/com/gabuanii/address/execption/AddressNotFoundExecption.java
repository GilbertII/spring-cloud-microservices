package com.gabuanii.address.execption;

public class AddressNotFoundExecption extends RuntimeException {
    public AddressNotFoundExecption(String message) {
        super(message);
    }

    public AddressNotFoundExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
