package com.commerce.cashregister.model.money;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("all")
public class MoneyNameValueMap {

    private static final Map<String, BigDecimal> denominationToValueMap = new TreeMap<>(new MoneyComparator());

    static {
        denominationToValueMap.put("500 zł", BigDecimal.valueOf(500));
        denominationToValueMap.put("200 zł", BigDecimal.valueOf(200));
        denominationToValueMap.put("100 zł", BigDecimal.valueOf(100));
        denominationToValueMap.put("50 zł", BigDecimal.valueOf(50));
        denominationToValueMap.put("20 zł", BigDecimal.valueOf(20));
        denominationToValueMap.put("10 zł", BigDecimal.valueOf(10));
        denominationToValueMap.put("5 zł", BigDecimal.valueOf(5));
        denominationToValueMap.put("2 zł", BigDecimal.valueOf(2));
        denominationToValueMap.put("1 zł", BigDecimal.valueOf(1));
        denominationToValueMap.put("50 gr", BigDecimal.valueOf(0.50));
        denominationToValueMap.put("20 gr", BigDecimal.valueOf(0.20));
        denominationToValueMap.put("10 gr", BigDecimal.valueOf(0.10));
        denominationToValueMap.put("5 gr", BigDecimal.valueOf(0.05));
        denominationToValueMap.put("2 gr", BigDecimal.valueOf(0.02));
        denominationToValueMap.put("1 gr", BigDecimal.valueOf(0.01));
    }

    public static BigDecimal getValue(String denomination) {
        return denominationToValueMap.get(denomination);
    }

    public static Set<String> getDenominations() {
        return denominationToValueMap.keySet();
    }
}
