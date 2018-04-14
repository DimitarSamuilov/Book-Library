package com.book.library.booklibrary.home.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested Resource not found",code = HttpStatus.NOT_FOUND)
public class NoSuchResourceException extends RuntimeException {
    public NoSuchResourceException(String custom_error_message) {
        super(custom_error_message);
    }
}
