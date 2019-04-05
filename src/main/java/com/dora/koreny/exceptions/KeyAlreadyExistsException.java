package com.dora.koreny.exceptions;

public class KeyAlreadyExistsException extends RuntimeException {

    public KeyAlreadyExistsException() {
        super("Key already exists.");
    }
}
