package com.facultate.biblioteca.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        LOGGER.error("Unhandled application exception", ex);
        model.addAttribute("mesajEroare", ex.getMessage());
        return "eroare";
    }
}
