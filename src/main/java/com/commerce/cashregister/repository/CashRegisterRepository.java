package com.commerce.cashregister.repository;

import com.commerce.cashregister.model.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, UUID> {
}
