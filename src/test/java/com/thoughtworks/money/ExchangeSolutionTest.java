package com.thoughtworks.money;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ExchangeSolutionTest {
    @Test
    public void should_exchange_money_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = new ExchangeSolution(238).exchange();
        HashMap<Currency, Integer> detail = exchangeSolution.details;
        assertEquals(13, detail.get(Currency.JPY).intValue());
        assertEquals(1, detail.get(Currency.HKD).intValue());
        assertEquals(1, detail.get(Currency.USD).intValue());
        assertEquals(15, exchangeSolution.currenciesCount());
    }
}
