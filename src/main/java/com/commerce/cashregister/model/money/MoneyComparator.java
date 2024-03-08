package com.commerce.cashregister.model.money;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class MoneyComparator implements Comparator<String>, Serializable {
  private static final Map<String, Integer> moneyValues = new HashMap<>();

  static {
    moneyValues.put("500 zł", 0);
    moneyValues.put("200 zł", 1);
    moneyValues.put("100 zł", 2);
    moneyValues.put("50 zł", 3);
    moneyValues.put("20 zł", 4);
    moneyValues.put("10 zł", 5);
    moneyValues.put("5 zł", 6);
    moneyValues.put("2 zł", 7);
    moneyValues.put("1 zł", 8);
    moneyValues.put("50 gr", 9);
    moneyValues.put("20 gr", 10);
    moneyValues.put("10 gr", 11);
    moneyValues.put("5 gr", 12);
    moneyValues.put("2 gr", 13);
    moneyValues.put("1 gr", 14);
  }

  @Override
  public int compare(String s, String t1) {
    return moneyValues.get(s).compareTo(moneyValues.get(t1));
  }
}
