package com.commerce.cashregister.service;

import com.commerce.cashregister.exception.NoCashRegisterException;
import com.commerce.cashregister.model.CashRegister;
import com.commerce.cashregister.repository.CashRegisterRepository;
import com.commerce.cashregister.rest.request.NewMoneyRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashRegisterServiceTest {

    @Mock CashRegisterRepository cashRegisterRepository;
    @InjectMocks CashRegisterService uut;

    @Test
    void createNewCashRegister() {
        // Given
        var newCashRegister = new CashRegister();
        when(cashRegisterRepository.save(any(CashRegister.class))).thenReturn(newCashRegister);

        // When
        var createdRegister = uut.createNewCashRegister();

        // Then
        assertThat(createdRegister).isEqualTo(newCashRegister);


    }


    @Test
    void getCashRegister_ok() {
        // Given
        var cashRegister = new CashRegister();
        var id = UUID.randomUUID();
        when(cashRegisterRepository.findById(id)).thenReturn(Optional.of(cashRegister));

        // When
        var foundCashRegister = uut.getCashRegister(id);

        // Then
        assertThat(foundCashRegister.getMoney()).isEqualTo(cashRegister.getMoney());
    }

    @Test
    void getCashRegister_notFound() {
        // Given
        var id = UUID.randomUUID();
        when(cashRegisterRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoCashRegisterException.class, () -> uut.getCashRegister(id));
    }

    @Test
    void addNewMoney() {
        // Given
        UUID id = UUID.randomUUID();
        NewMoneyRequest request = new NewMoneyRequest(1,1,1,1,1,1,1,1,1, 1, 1,1, 1, 1, 1);
        CashRegister expectedCashRegister = new CashRegister();
        expectedCashRegister.addMoneyFromRequest(request);
        when(cashRegisterRepository.findById(id)).thenReturn(Optional.of(new CashRegister()));
        when(cashRegisterRepository.save(any(CashRegister.class))).thenAnswer(i -> i.getArguments()[0]);

        //When
        CashRegister updatedCashRegister = uut.addNewMoney(id, request);

        //Then
        assertEquals(updatedCashRegister.getMoney(), expectedCashRegister.getMoney());
    }


    @Test
    void addNewMoney_whenMoneyIsPresent() {
        // Given
        UUID id = UUID.randomUUID();
        NewMoneyRequest request = new NewMoneyRequest(1,1,1,1,1,1,1,1,1, 1, 1,1, 1, 1, 1);
        CashRegister cashRegister = new CashRegister();
        cashRegister.addMoneyFromRequest(request);
        CashRegister expectedCashRegister = new CashRegister();
        expectedCashRegister.addMoneyFromRequest(request);
        expectedCashRegister.addMoneyFromRequest(request);
        when(cashRegisterRepository.findById(id)).thenReturn(Optional.of(cashRegister));
        when(cashRegisterRepository.save(any(CashRegister.class))).thenAnswer(i -> i.getArguments()[0]);

        //When
        CashRegister updatedCashRegister = uut.addNewMoney(id, request);

        //Then
        assertThat(updatedCashRegister.getMoney()).isEqualTo(expectedCashRegister.getMoney());
    }



}