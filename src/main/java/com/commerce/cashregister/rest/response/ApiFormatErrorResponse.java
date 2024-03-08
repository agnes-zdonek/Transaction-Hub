package com.commerce.cashregister.rest.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ApiFormatErrorResponse extends ApiErrorResponse {
    private final List<String> errors;
    public ApiFormatErrorResponse(String message, List<String> errors) {
        super(HttpStatus.BAD_REQUEST, message);
        this.errors = errors;
    }
    public ApiFormatErrorResponse(String message, String error) {
        super(HttpStatus.BAD_REQUEST, message);
        this.errors = List.of(error);
    }
}
