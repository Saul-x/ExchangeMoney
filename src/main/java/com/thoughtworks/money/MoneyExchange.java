package com.thoughtworks.money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoneyExchange {
    public static ExchangeSolution exchange(int total, Map<Integer, String> moneyValues) {
        List<Integer> moneyKinds = new ArrayList<>(moneyValues.keySet());
        final List<ExchangeSolution> exchangeSolutions = IntStream.rangeClosed(0, total).mapToObj(amount -> new ExchangeSolution(amount, new HashMap<>()))
                .collect(Collectors.toList());
        exchangeSolutions.stream()
                .filter(exchangeSolution -> exchangeSolution.total > 0)
                .forEach(exchangeSolution -> {
                    int minMoneyCount = Integer.MAX_VALUE;
                    int optimizeKind = 0;
                    for (int index = 0; index < moneyKinds.size(); index++) {
                        if (exchangeSolution.total >= moneyKinds.get(index)) {
                            if (minMoneyCount > exchangeSolutions.get(exchangeSolution.total - moneyKinds.get(index)).currenciesCount()) {
                                minMoneyCount = exchangeSolutions.get(exchangeSolution.total - moneyKinds.get(index)).currenciesCount();
                                optimizeKind = index;
                            }
                        }
                    }
            exchangeSolution.getDetails().putAll(exchangeSolutions.get(exchangeSolution.total - moneyKinds.get(optimizeKind)).details);
            exchangeSolution.getDetails().computeIfPresent(moneyValues.get(moneyKinds.get(optimizeKind)), (key, value) -> ++value);
            exchangeSolution.getDetails().putIfAbsent(moneyValues.get(moneyKinds.get(optimizeKind)), 1);
        });
        return exchangeSolutions.get(total);
    }
}
