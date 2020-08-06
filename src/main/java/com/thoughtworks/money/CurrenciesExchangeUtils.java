package com.thoughtworks.money;

import java.util.Comparator;
import java.util.stream.Stream;

public class CurrenciesExchangeUtils {
    public static String exchangeMoneyWithLeastCurrencies(int num, Currency unit) {
        ExchangeSolution exchange = new ExchangeSolution(num * unit.value).exchange();
        return num + unit.name + "= " + exchange.detailsDescription();
    }

    public static String exchangeMoneyWithMostCurrencies(int num, Currency unit) {
        if (num == 0) return "";
        return Stream.of(Currency.values())
                .min(Comparator.comparing(currency -> currency.value))
                .map(cheapest -> num + unit.name + "= " + num * unit.value / cheapest.value + cheapest.name)
                .orElse("");
    }
}
