package com.example.carros.api.exception;

import java.io.Serializable;
import java.net.http.HttpHeaders;
import java.nio.file.AccessDeniedException;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity erroNotFoun(Exception ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity erroBadRequest(Exception ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionError("Operação não permitida"));
    }
}

class ExceptionError {
    public String error;

    ExceptionError(String error) {
        this.error = error;
    }
}
