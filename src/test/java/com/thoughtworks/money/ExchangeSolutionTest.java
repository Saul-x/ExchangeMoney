package com.thoughtworks.money;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ExchangeSolutionTest {
    @Test
    public void should_exchange_money_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(106, Currency.CNY);
        HashMap<Currency, Integer> detail = exchangeSolution.getDetails();
        assertEquals(1, detail.get(Currency.CNY).intValue());
        assertEquals(7, detail.get(Currency.EUR).intValue());
        assertEquals(1, detail.get(Currency.USD).intValue());
        assertEquals(9, exchangeSolution.currenciesCount());
    }
}
