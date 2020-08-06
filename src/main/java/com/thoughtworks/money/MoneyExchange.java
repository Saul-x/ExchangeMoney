package com.thoughtworks.money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyExchange {
    public static ExchangeSolution exchange(int total, Map<Integer, String> moneyValues) {
        List<Integer> moneyKinds = new ArrayList<>(moneyValues.keySet());
        ExchangeSolution[] exchangeSolutions = new ExchangeSolution[total + 1];
        exchangeSolutions[0] = new ExchangeSolution(new HashMap<>());
        for (int currentTotal = 1; currentTotal < exchangeSolutions.length; currentTotal++) {
            int minMoneyCount = Integer.MAX_VALUE;
            int optimizeKind = 0;
            for (int index = 0; index < moneyKinds.size(); index++) {
                if (currentTotal >= moneyKinds.get(index)) {
                    if (minMoneyCount > exchangeSolutions[currentTotal - moneyKinds.get(index)].currenciesCount()) {
                        minMoneyCount = exchangeSolutions[currentTotal - moneyKinds.get(index)].currenciesCount();
                        optimizeKind = index;
                    }
                }
            }
            HashMap<String, Integer> details = (HashMap<String, Integer>) exchangeSolutions[currentTotal - moneyKinds.get(optimizeKind)].details.clone();
            details.computeIfPresent(moneyValues.get(moneyKinds.get(optimizeKind)), (key, value) -> ++value);
            details.putIfAbsent(moneyValues.get(moneyKinds.get(optimizeKind)), 1);
            exchangeSolutions[currentTotal] = new ExchangeSolution(details);
        }
        return exchangeSolutions[total];
    }
}
