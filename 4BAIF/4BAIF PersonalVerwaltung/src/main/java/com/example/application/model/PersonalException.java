package com.example.application.model;

public class PersonalException extends Exception {
    public PersonalException(String message) {
        super(message);
    }

    public PersonalException(String message, Throwable cause) {
        super(message, cause);
    }
}
