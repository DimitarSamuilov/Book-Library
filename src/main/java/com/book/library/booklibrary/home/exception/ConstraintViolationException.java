package com.book.library.booklibrary.home.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Conflict with resources")
public class ConstraintViolationException extends RuntimeException {
}
