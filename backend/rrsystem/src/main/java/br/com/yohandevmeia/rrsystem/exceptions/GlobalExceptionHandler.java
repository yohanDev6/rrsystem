package br.com.yohandevmeia.rrsystem.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle 400 error
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", "Bad request");
        model.addAttribute("message", ex.getMessage());
        return "errors/badrequest";
    }
    
    // Handle 404 error
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        model.addAttribute("error", "Entity not found");
        model.addAttribute("message", ex.getMessage());
        return "errors/notfound";
    }

    // Handle generic Exception (500 error)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "An error occurred");
        model.addAttribute("message", ex.getMessage());
        return "errors/internalservererror";
    }
}
