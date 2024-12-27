package com.micro_serivce.client.helpers.CustomExceptions;

public class CreationFailedException extends RuntimeException {
    public CreationFailedException(String message) {
        super(message);
    }
}
