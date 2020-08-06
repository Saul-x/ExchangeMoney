package com.thoughtworks.money;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class MoneyExchangeTest {
    public HashMap<Integer, String> currenciesDictionary = new LinkedHashMap<>();

    @Before
    public void setUp() {
        currenciesDictionary.put(1, "日元");
        currenciesDictionary.put(15, "港币");
        currenciesDictionary.put(30, "人民币");
        currenciesDictionary.put(210, "美元");
        currenciesDictionary.put(420, "欧元");
    }

    @Test
    public void should_exchange_money_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = MoneyExchange.exchange(238, currenciesDictionary);
        HashMap<String, Integer> detail = exchangeSolution.details;
        assertEquals(13, detail.get("日元").intValue());
        assertEquals(1, detail.get("港币").intValue());
        assertEquals(1, detail.get("美元").intValue());
        assertEquals(15, exchangeSolution.currenciesCount);
    }
}
