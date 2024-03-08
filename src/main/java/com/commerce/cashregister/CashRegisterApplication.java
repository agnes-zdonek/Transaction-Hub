package com.commerce.cashregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.commerce.cashregister.model")
public class CashRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashRegisterApplication.class, args);
	}
}
