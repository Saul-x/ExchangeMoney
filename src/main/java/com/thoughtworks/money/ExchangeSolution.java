package com.thoughtworks.money;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return Stream.of(Currency.values())
                .filter(currency -> total >= currency.value)
                .reduce((prev, next) -> predictCurrenciesCount(exchangeSolutions, total, prev)
                        > predictCurrenciesCount(exchangeSolutions, total, next) ? next : prev)
                .orElse(Currency.JPY);
    }

    private int predictCurrenciesCount(List<ExchangeSolution> exchangeSolutions, int total, Currency currency) {
        return exchangeSolutions.get(total - currency.value).currenciesCount();
    }
}
