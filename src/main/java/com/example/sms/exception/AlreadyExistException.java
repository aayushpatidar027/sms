package com.example.sms.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class AlreadyExistException extends RuntimeException {
     public AlreadyExistException(String message) {
        super(message);
    }
}
