package com.thoughtworks.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExchangeSolution {
    private int total;
    private HashMap<Currency, Integer> details;

    private ExchangeSolution(int total, HashMap<Currency, Integer> details) {
        this.total = total;
        this.details = details;
    }

    public Map<Currency, Integer> getDetails() {
        return details;
    }

    public int currenciesCount() {
        return details.values().stream().reduce(Math::addExact).orElse(0);
    }

    public String detailsDescription() {
       return details.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getValue() + entry.getKey().name)
                .collect(Collectors.joining(" + "));
    }

    private void updateDetail(HashMap<Currency, Integer> prevDetails, Currency optimizedCurrency) {
        this.getDetails().putAll(prevDetails);
        this.getDetails().computeIfPresent(optimizedCurrency, (key, value) -> ++value);
        this.getDetails().putIfAbsent(optimizedCurrency, 1);
    }

    public static ExchangeSolution exchange(int num, Currency unit) {
        final List<ExchangeSolution> exchangeSolutions = IntStream.rangeClosed(0, num * unit.value).mapToObj(amount -> new ExchangeSolution(amount, new HashMap<>()))
                .collect(Collectors.toList());
        exchangeSolutions.stream().filter(exchangeSolution -> exchangeSolution.total > 0).forEach(exchangeSolution -> {
            Currency optimizedCurrency = getOptimizedCurrency(exchangeSolutions, exchangeSolution.total);
            exchangeSolution.updateDetail(
                    exchangeSolutions.get(getSubExchangeSolutionIndex(optimizedCurrency, exchangeSolution.total)).details,
                    optimizedCurrency
            );
        });
        return exchangeSolutions.get(num * unit.value);
    }

    private static int getSubExchangeSolutionIndex(Currency optimizedCurrency, int total) {
        return total - optimizedCurrency.value;
    }

    private static Currency getOptimizedCurrency(List<ExchangeSolution> exchangeSolutions, int total) {
        return Stream.of(Currency.values())
                .filter(currency -> total >= currency.value)
                .reduce((prev, next) -> predictCurrenciesCount(exchangeSolutions, total, prev)
                        > predictCurrenciesCount(exchangeSolutions, total, next) ? next : prev)
                .orElse(Currency.JPY);
    }

    private static int predictCurrenciesCount(List<ExchangeSolution> exchangeSolutions, int total, Currency currency) {
        return exchangeSolutions.get(getSubExchangeSolutionIndex(currency, total)).currenciesCount();
    }
}
