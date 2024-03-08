package com.commerce.cashregister.rest.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class ApiErrorResponse {
    private final HttpStatus status;
    private final Instant timestamp;
    private final String message;

    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }
}
