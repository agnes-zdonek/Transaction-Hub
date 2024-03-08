package com.commerce.cashregister.exception.handler;

import com.commerce.cashregister.exception.*;
import com.commerce.cashregister.rest.response.ApiErrorResponse;
import com.commerce.cashregister.rest.response.ApiFormatErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


// RestControllerAdvice = @ControllerAdvice + @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientMoneyException.class)
    protected ResponseEntity<ApiErrorResponse> handleInsufficientMoneyException(
            InsufficientMoneyException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ApiErrorResponse(ex.getStatus(), ex.getMessage()));
    }

    @ExceptionHandler(TooManyDecimalPlacesException.class)
    protected ResponseEntity<ApiErrorResponse> handleTooManyDecimalPlacesException(
            TooManyDecimalPlacesException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ApiErrorResponse(ex.getStatus(), ex.getMessage()));
    }

    @ExceptionHandler(NegativeMoneyException.class)
    protected ResponseEntity<ApiErrorResponse> handleNegativeMoneyException(
            NegativeMoneyException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ApiErrorResponse(ex.getStatus(), ex.getMessage()));
    }

    @ExceptionHandler(NoCashRegisterException.class)
    protected ResponseEntity<ApiErrorResponse> handleNoCashRegisterException(
            NoCashRegisterException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ApiErrorResponse(ex.getStatus(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = "Parameter '%s' should be of type %s".formatted(ex.getName(), ex.getRequiredType().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiFormatErrorResponse(ex.getMessage(), error));
    }

}