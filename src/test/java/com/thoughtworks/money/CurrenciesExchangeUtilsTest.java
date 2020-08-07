package com.thoughtworks.money;

import org.junit.Assert;
import org.junit.Test;

public class CurrenciesExchangeUtilsTest {
    @Test
    public void should_exchange_106_CNY_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(106, Currency.CNY);
        Assert.assertEquals("106人民币= 1人民币 + 1美元 + 7欧元", result);
    }

    @Test
    public void should_exchange_1_CNY_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(1, Currency.CNY);
        Assert.assertEquals("1人民币= 1人民币", result);
    }

    @Test
    public void should_exchange_27_USD_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(27, Currency.USD);
        Assert.assertEquals("27美元= 1美元 + 13欧元", result);
    }

    @Test
    public void should_exchange_18726_HKD_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(18726, Currency.HKD);
        Assert.assertEquals("18726港币= 4人民币 + 1美元 + 668欧元", result);
    }

    @Test
    public void should_exchange_678_EUR_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(678, Currency.EUR);
        Assert.assertEquals("678欧元= 678欧元", result);
    }

    @Test
    public void should_exchange_0_EUR_with_least_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(0, Currency.EUR);
        Assert.assertEquals("", result);
    }

    @Test
    public void should_exchange_1_EUR_with_most_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithMostCurrencies(678, Currency.EUR);
        Assert.assertEquals("678欧元= 284760日元", result);
    }

    @Test
    public void should_exchange_0_EUR_with_most_currencies() {
        String result = CurrenciesExchangeUtils.exchangeMoneyWithMostCurrencies(0, Currency.EUR);
        Assert.assertEquals("", result);
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_negative_currency_with_most_currencies() {
        CurrenciesExchangeUtils.exchangeMoneyWithMostCurrencies(-1, Currency.EUR);
    }

    @Test(expected = AssertionError.class)
    public void should_throw_error_negative_currency_with_least_currencies() {
        CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies(-1, Currency.EUR);
    }
}
