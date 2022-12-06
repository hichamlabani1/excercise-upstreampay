package com.upstreampay.exercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class ResponseError {
    private String message;
    private Throwable throwable;

}
