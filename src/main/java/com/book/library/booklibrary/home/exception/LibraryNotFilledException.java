package com.book.library.booklibrary.home.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Library details are not filled",code = HttpStatus.NOT_FOUND)
public class LibraryNotFilledException extends RuntimeException {
}
