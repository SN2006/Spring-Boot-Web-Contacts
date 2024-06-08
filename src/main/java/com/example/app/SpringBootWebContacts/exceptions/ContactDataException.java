package com.example.app.SpringBootWebContacts.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ContactDataException extends RuntimeException {

    private final Map<String, String> errors;

    public ContactDataException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
