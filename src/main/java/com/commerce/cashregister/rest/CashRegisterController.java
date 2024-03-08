package com.commerce.cashregister.rest;

import com.commerce.cashregister.model.CashRegister;
import com.commerce.cashregister.rest.request.NewMoneyRequest;
import com.commerce.cashregister.service.CashRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cash-registers")
public class CashRegisterController {

    @Autowired
    private CashRegisterService cashRegisterService;

    @GetMapping()
    public List<CashRegister> getAllCashRegisters() {
        return cashRegisterService.getAllCashRegisters();
    }

    @GetMapping("/{id}")
    public CashRegister getCashRegister(@PathVariable UUID id) {
        return cashRegisterService.getCashRegister(id);
    }

    @PostMapping()
    public CashRegister createNewCashRegister() {
        return cashRegisterService.createNewCashRegister();
    }

    @PostMapping("/{id}")
    public CashRegister addNewMoneyToCashRegister(@PathVariable UUID id, @RequestBody NewMoneyRequest request) {
        return cashRegisterService.addNewMoney(id, request);
    }

    @GetMapping("/{id}/change")
    public Map<String, Integer> giveOutChange(@PathVariable UUID id, @RequestParam BigDecimal value) {
        return cashRegisterService.giveOutChange(id, value);
    }

    @GetMapping("/client")
    public CashRegister getCashRegisterThroughHeader(@RequestHeader("Client-Id") UUID id) {
        return cashRegisterService.getCashRegister(id);
    }
}
