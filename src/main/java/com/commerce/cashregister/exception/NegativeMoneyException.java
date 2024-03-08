package com.commerce.cashregister.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class NegativeMoneyException extends RuntimeException {
    private final HttpStatus status;
    public NegativeMoneyException(UUID id) {
        super("Value must be positive (for cash register %s)".formatted(id));
        status = HttpStatus.BAD_REQUEST;
    }

}
