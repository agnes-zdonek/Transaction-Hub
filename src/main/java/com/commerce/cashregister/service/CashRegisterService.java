package com.commerce.cashregister.service;

import com.commerce.cashregister.exception.NegativeMoneyException;
import com.commerce.cashregister.exception.NoCashRegisterException;
import com.commerce.cashregister.exception.TooManyDecimalPlacesException;
import com.commerce.cashregister.model.CashRegister;
import com.commerce.cashregister.repository.CashRegisterRepository;
import com.commerce.cashregister.rest.request.NewMoneyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashRegisterService {


    private final CashRegisterRepository cashRegisterRepository;
    public List<CashRegister> getAllCashRegisters() {
        return cashRegisterRepository.findAll();
    }

    public CashRegister createNewCashRegister() {
        var newRegister = new CashRegister();
        return cashRegisterRepository.save(newRegister);
    }

    public CashRegister getCashRegister(UUID id) {
        return cashRegisterRepository.findById(id).orElseThrow(() -> new NoCashRegisterException(id));
    }

    public CashRegister addNewMoney(UUID id, NewMoneyRequest request) {
        CashRegister cashRegister = getCashRegister(id);
        cashRegister.addMoneyFromRequest(request);
        return cashRegisterRepository.save(cashRegister);
    }


    public Map<String, Integer> giveOutChange(UUID id, BigDecimal value) {

        if(value.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeMoneyException(id);
        } else if(value.scale()>2) throw new TooManyDecimalPlacesException(id);
        var cashRegister = getCashRegister(id);
        var change = cashRegister.giveChange(value);
        cashRegisterRepository.save(cashRegister);
        return change;
    }


}
