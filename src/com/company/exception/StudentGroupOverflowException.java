package com.company.exception;

public class StudentGroupOverflowException extends RuntimeException {
    public StudentGroupOverflowException(String message) {
        super(message);
    }
}
