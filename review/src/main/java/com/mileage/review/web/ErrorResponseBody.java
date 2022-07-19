package com.mileage.review.web;

import org.springframework.http.HttpStatus;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ErrorResponseBody {
    private static final String INTERNAL_SERVER_ERROR = "요청을 처리하지 못했습니다.";
    
    private ErrorResponse error;
    
    public ErrorResponseBody(ErrorResponse error) { this.error = error; }
    
    /* 사전에 정의된 예외들에 대한 응답 */
    public static ErrorResponseBody of(ExecutionException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), requestUri));
    }
    
    public static ErrorResponseBody of(InterruptedException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), requestUri));
    }
    
    public static ErrorResponseBody of(TimeoutException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), requestUri));
    }
    
    /* 사전에 정의되지 않은 예외를 상태코드로 응답하기 */
    public static ErrorResponseBody badRequestOf(Exception exception, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, HttpStatus.BAD_REQUEST.value(), requestUri));
    }
    
    /* ********************** Getters for Jackson ********************** */
    public ErrorResponse getError() { return error; }
}
