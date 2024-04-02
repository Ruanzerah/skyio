package com.ruanzerah.skyio.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UsernameNotFoundException e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
