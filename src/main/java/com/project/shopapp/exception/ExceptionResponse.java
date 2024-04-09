package com.project.shopapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionResponse extends RuntimeException {
    public ExceptionResponse(String message) {
        super(message);
    }
}
