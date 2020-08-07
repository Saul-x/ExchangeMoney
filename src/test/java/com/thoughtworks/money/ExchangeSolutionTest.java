package com.thoughtworks.money;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExchangeSolutionTest {
    @Test
    public void should_exchange_106_CNY_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(106, Currency.CNY);
        Map<Currency, Integer> detail = exchangeSolution.getDetails();
        assertEquals(1, detail.get(Currency.CNY).intValue());
        assertEquals(7, detail.get(Currency.EUR).intValue());
        assertEquals(1, detail.get(Currency.USD).intValue());
        assertEquals(9, exchangeSolution.currenciesCount());
    }

    @Test
    public void should_exchange_106_USD_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(106, Currency.USD);
        Map<Currency, Integer> detail = exchangeSolution.getDetails();
        assertEquals(53, detail.get(Currency.EUR).intValue());
        assertEquals(53, exchangeSolution.currenciesCount());
    }

    @Test
    public void should_exchange_144_HKD_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(144, Currency.HKD);
        Map<Currency, Integer> detail = exchangeSolution.getDetails();
        assertEquals(2, detail.get(Currency.CNY).intValue());
        assertEquals(5, detail.get(Currency.EUR).intValue());
        assertEquals(7, exchangeSolution.currenciesCount());
    }

    @Test
    public void should_exchange_1444_JPY_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(1444, Currency.JPY);
        Map<Currency, Integer> detail = exchangeSolution.getDetails();
        assertEquals(4, detail.get(Currency.JPY).intValue());
        assertEquals(6, detail.get(Currency.CNY).intValue());
        assertEquals(3, detail.get(Currency.EUR).intValue());
        assertEquals(13, exchangeSolution.currenciesCount());
    }

    @Test
    public void should_exchange_0_USD_with_least_number_of_currencies() {
        ExchangeSolution exchangeSolution = ExchangeSolution.exchange(0, Currency.USD);
        Map<Currency, Integer> detail = exchangeSolution.getDetails();
        assertTrue(detail.isEmpty());
        assertEquals(0, exchangeSolution.currenciesCount());
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_negative_currency_with_least_currencies() {
        ExchangeSolution.exchange(-1, Currency.EUR);
    }
}
