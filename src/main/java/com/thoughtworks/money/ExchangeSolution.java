package com.thoughtworks.money;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExchangeSolution {
    int total;
    HashMap<Currency, Integer> details;

    public ExchangeSolution(int total, HashMap<Currency, Integer> details) {
        this.total = total;
        this.details = details;
    }

    public ExchangeSolution(int total) {
        this.total = total;
    }

    public HashMap<Currency, Integer> getDetails() {
        return details;
    }

    public int currenciesCount() {
        return details.values().stream().reduce(Math::addExact).orElse(0);
    }

    public ExchangeSolution exchange() {
        final List<ExchangeSolution> exchangeSolutions = IntStream.rangeClosed(0, total).mapToObj(amount -> new ExchangeSolution(amount, new HashMap<>()))
                .collect(Collectors.toList());
        exchangeSolutions.stream()
                .filter(exchangeSolution -> exchangeSolution.total > 0)
                .forEach(exchangeSolution -> {
                    Currency optimizedCurrency = getOptimizedCurrency(exchangeSolutions,exchangeSolution.total);
                    exchangeSolution.getDetails().putAll(exchangeSolutions.get(exchangeSolution.total - optimizedCurrency.value).details);
                    exchangeSolution.getDetails().computeIfPresent(optimizedCurrency, (key, value) -> ++value);
                    exchangeSolution.getDetails().putIfAbsent(optimizedCurrency, 1);
                });
        return exchangeSolutions.get(total);
    }

    private Currency getOptimizedCurrency(List<ExchangeSolution> exchangeSolutions, int total) {
        int minCurrenciesCount = Integer.MAX_VALUE;
        Currency optimizedCurrency = Currency.JPY;
        for (Currency currency : Currency.values()) {
            if (total >= currency.value) {
                if (minCurrenciesCount > exchangeSolutions.get(total - currency.value).currenciesCount()) {
                    minCurrenciesCount = exchangeSolutions.get(total - currency.value).currenciesCount();
                    optimizedCurrency = currency;
                }
            }
        }
        return optimizedCurrency;
    }
}
