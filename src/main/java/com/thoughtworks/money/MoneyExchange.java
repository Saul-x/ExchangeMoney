package com.thoughtworks.money;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoneyExchange {
    public static ExchangeSolution exchange(int total) {
        final List<ExchangeSolution> exchangeSolutions = IntStream.rangeClosed(0, total).mapToObj(amount -> new ExchangeSolution(amount, new HashMap<>()))
                .collect(Collectors.toList());
        exchangeSolutions.stream()
                .filter(exchangeSolution -> exchangeSolution.total > 0)
                .forEach(exchangeSolution -> {
                    int minCurrenciesCount = Integer.MAX_VALUE;
                    Currency optimizedCurrency = Currency.JPY;
                    for (Currency currency : Currency.values()) {
                        if (exchangeSolution.total >= currency.value) {
                            if (minCurrenciesCount > exchangeSolutions.get(exchangeSolution.total - currency.value).currenciesCount()) {
                                minCurrenciesCount = exchangeSolutions.get(exchangeSolution.total - currency.value).currenciesCount();
                                optimizedCurrency = currency;
                            }
                        }
                    }
            exchangeSolution.getDetails().putAll(exchangeSolutions.get(exchangeSolution.total - optimizedCurrency.value).details);
            exchangeSolution.getDetails().computeIfPresent(optimizedCurrency, (key, value) -> ++value);
            exchangeSolution.getDetails().putIfAbsent(optimizedCurrency, 1);
        });
        return exchangeSolutions.get(total);
    }
}
