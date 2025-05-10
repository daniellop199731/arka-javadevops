package com.bancolombia.arka_javadevops.controllers.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.bancolombia.arka_javadevops.utils.ResponseObject;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static ResponseObject rObj;

    //WebExchangeBindException: Se dispara cuando @Valid incumple algun atributo de la clase entity
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ResponseObject> handleWebExchangeBindException(WebExchangeBindException ex) {
        rObj = new ResponseObject();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        rObj.setAsNotSuccessfully();
        rObj.setMsj(errors);
        return ResponseEntity.badRequest().body(rObj);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseObject> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        rObj = new ResponseObject();
        rObj.setAsNotSuccessfully();
        rObj.setMsj(ex.getMessage());
        return ResponseEntity.badRequest().body(rObj);
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ResponseObject> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex){
        rObj = new ResponseObject();
        rObj.setAsNotSuccessfully();
        rObj.setMsj("Falla por conexion a BD: ".concat(ex.getMessage()));        
        return ResponseEntity.internalServerError().body(rObj);
    }



    
}
