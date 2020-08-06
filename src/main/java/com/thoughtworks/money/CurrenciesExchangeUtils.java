package com.thoughtworks.money;

public class CurrenciesExchangeUtils {
    public static String exchangeMoneyWithLeastCurrencies(int num, Currency unit) {
        ExchangeSolution exchange = new ExchangeSolution(num * unit.value).exchange();
        return num + unit.name + "= " + exchange.detailsDescription();
    }
}
