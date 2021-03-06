package com.book.library.booklibrary.home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = Exception.class)
    public String globalException(Exception ex, Model model, HttpServletResponse response) {
        Class<? extends Exception> aClass = ex.getClass();
        HttpStatus status = HttpStatus.CONFLICT;
        String errorMessage = status.getReasonPhrase();
        if (aClass.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus annotation = aClass.getAnnotation(ResponseStatus.class);
            status = annotation.value();
            errorMessage = annotation.reason();
        }
        model.addAttribute("status", status);
        model.addAttribute("errorMessage", errorMessage );
        return "error/custom";
    }
}
