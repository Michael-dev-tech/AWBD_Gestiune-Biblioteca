package com.facultate.biblioteca.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Această adnotare prinde orice eroare din aplicație
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        // Trimitem mesajul erorii către o pagină HTML de design
        model.addAttribute("mesajEroare", ex.getMessage());
        return "eroare";
    }
}