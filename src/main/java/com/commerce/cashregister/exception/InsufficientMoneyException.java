package com.commerce.cashregister.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class InsufficientMoneyException extends RuntimeException {

    private final HttpStatus status;
    public InsufficientMoneyException(UUID id, BigDecimal moneyValue) {
        super("I'm not able to give out this exact change: %.2f (for cash register %s)".formatted(moneyValue, id));
        status = HttpStatus.CONFLICT;
    }
}