package com.upstreampay.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleUpdateTransactionException(BusinessException e ){
        ResponseError responseError = new ResponseError(e.getMessage(), e);
        return new ResponseEntity<>(responseError , HttpStatus.FORBIDDEN);
    }
}
