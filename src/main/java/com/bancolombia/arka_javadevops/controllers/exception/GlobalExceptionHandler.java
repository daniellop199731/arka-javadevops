package com.bancolombia.arka_javadevops.controllers.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    //MethodArgumentNotValidException: Se dispara cuando @Valid incumple algun atributo de la clase entity
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body("Error con los datos enviados: ".concat(ex.getMessage()));
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<?> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex){       
        return ResponseEntity.internalServerError().body("Falla por conexion a BD: ".concat(ex.getMessage()));
    }



    
}
