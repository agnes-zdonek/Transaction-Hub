package com.commerce.cashregister.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class NoCashRegisterException extends RuntimeException{

    private final HttpStatus status;
    public NoCashRegisterException(UUID id) {
        super("No cash register of such id found: id %s)".formatted(id));
        status = HttpStatus.BAD_REQUEST;
    }
}

