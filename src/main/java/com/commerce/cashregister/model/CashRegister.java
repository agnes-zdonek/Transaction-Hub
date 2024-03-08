package com.commerce.cashregister.model;

import com.commerce.cashregister.exception.InsufficientMoneyException;
import com.commerce.cashregister.model.money.MoneyComparator;
import com.commerce.cashregister.model.money.MoneyNameValueMap;
import com.commerce.cashregister.rest.request.NewMoneyRequest;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Entity
@Getter
public class CashRegister {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @SuppressWarnings("all")
    @Column(columnDefinition = "binary(1000)")
    private TreeMap<String, Integer> money;

    public CashRegister() {
        money = new TreeMap<>(new MoneyComparator());
        money.put("500 zł", 0);
        money.put("200 zł", 0);
        money.put("100 zł", 0);
        money.put("50 zł", 0);
        money.put("20 zł", 0);
        money.put("10 zł", 0);
        money.put("5 zł", 0);
        money.put("2 zł", 0);
        money.put("1 zł", 0);
        money.put("50 gr", 0);
        money.put("20 gr", 0);
        money.put("10 gr", 0);
        money.put("5 gr", 0);
        money.put("2 gr", 0);
        money.put("1 gr", 0);
    }

    public void addMoneyFromRequest(NewMoneyRequest request) {
        money.put("500 zł", money.get("500 zł") + request.getFiveHundredZl());
        money.put("200 zł", money.get("200 zł") + request.getTwoHundredZl());
        money.put("100 zł", money.get("100 zł") + request.getOneHundredZl());
        money.put("50 zł", money.get("50 zł") + request.getFiftyZl());
        money.put("20 zł", money.get("20 zł") + request.getTwentyZl());
        money.put("10 zł", money.get("10 zł") + request.getTenZl());
        money.put("5 zł", money.get("5 zł") + request.getFiveZl());
        money.put("2 zł", money.get("2 zł") + request.getTwoZl());
        money.put("1 zł", money.get("1 zł") + request.getOneZl());
        money.put("50 gr", money.get("50 gr") + request.getFiftyGr());
        money.put("20 gr", money.get("20 gr") + request.getTwentyGr());
        money.put("10 gr", money.get("10 gr") + request.getTenGr());
        money.put("5 gr", money.get("5 gr") + request.getFiveGr());
        money.put("2 gr", money.get("2 gr") + request.getTwoGr());
        money.put("1 gr", money.get("1 gr") + request.getOneGr());
    }

    private int calculateBillsToGive(BigDecimal value, BigDecimal denomination, String denominationKey) {
        return Math.min(
                    value.divide(denomination, RoundingMode.FLOOR).intValue(),
                    money.get(denominationKey));
    }

    public Map<String, Integer> giveChange(BigDecimal changeToGive) {
        Map<String, Integer> change = new TreeMap<>(new MoneyComparator());
        var yetToGive = changeToGive;
        for (var denomination : MoneyNameValueMap.getDenominations()) {
            var moneyValue = MoneyNameValueMap.getValue(denomination);
            int howManyBills = calculateBillsToGive(yetToGive, moneyValue, denomination);
            money.put(denomination, money.get(denomination) - howManyBills);
            change.put(denomination, howManyBills);
            yetToGive = yetToGive.subtract(moneyValue.multiply(BigDecimal.valueOf(howManyBills)));
        }
        if(yetToGive.compareTo(BigDecimal.ZERO) != 0) throw new InsufficientMoneyException(id, changeToGive);
        return change;
    }

}
