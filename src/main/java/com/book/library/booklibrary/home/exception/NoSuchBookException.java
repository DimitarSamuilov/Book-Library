package com.book.library.booklibrary.home.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such book",code = HttpStatus.NOT_FOUND)
public class NoSuchBookException extends NoSuchResourceException {
    public NoSuchBookException(String custom_error_message) {
        super(custom_error_message);
    }
}
