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
        String das = "das";
        Class<? extends Exception> aClass = ex.getClass();
        String statusCode = HttpStatus.CONFLICT.toString();
        if (aClass.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus annotation = aClass.getAnnotation(ResponseStatus.class);
            statusCode = annotation.value().toString();
        }
        model.addAttribute("title",statusCode);
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/custom";
    }
}
