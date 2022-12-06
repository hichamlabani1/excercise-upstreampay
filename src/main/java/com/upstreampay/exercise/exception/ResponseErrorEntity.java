package com.upstreampay.exercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseErrorEntity {
    private String message;
    private Throwable throwable;

}
