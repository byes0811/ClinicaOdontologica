package com.proyectofinal.clinicaodontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler({ResourceNotFoundException.class, Exception.class, BadRequestException.class})
    public ResponseEntity<ErrorDetails> procesarErrorRecursoNoEncontrado(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getRequestURI(),HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
