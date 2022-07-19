package com.mileage.review.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionMessage {
    @Getter
    private String message;
    
    private static final String INTERNAL_SERVER_ERROR = "요청을 처리하지 못했습니다.";
    
    public ExceptionMessage(String message) { this.message = message; }
    
    public static ExceptionMessage of(ExecutionException exception) {
        return new ExceptionMessage(INTERNAL_SERVER_ERROR);
    }
    
    public static ExceptionMessage of(InterruptedException exception) {
        return new ExceptionMessage(INTERNAL_SERVER_ERROR);
    }
    
    public static ExceptionMessage of(TimeoutException exception) {
        return new ExceptionMessage(INTERNAL_SERVER_ERROR);
    }
}
