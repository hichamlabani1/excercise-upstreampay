package com.upstreampay.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UpdateTransactionException.class})
    public ResponseEntity<Object> handleUpdateTransactionException(UpdateTransactionException e ){
        ResponseErrorEntity responseErrorEntity = new ResponseErrorEntity(e.getMessage(), e);
        return new ResponseEntity<>(responseErrorEntity, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e ){
        ResponseErrorEntity responseErrorEntity = new ResponseErrorEntity(e.getMessage(), e);
        return new ResponseEntity<>(responseErrorEntity, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {StatusTransactionException.class})
    public ResponseEntity<Object> handleNotFoundException(StatusTransactionException e ){
        ResponseErrorEntity responseErrorEntity = new ResponseErrorEntity(e.getMessage(), e);
        return new ResponseEntity<>(responseErrorEntity, HttpStatus.BAD_REQUEST);
    }
}
