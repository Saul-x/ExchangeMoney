package com.thoughtworks.money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyExchange {
    public static ExchangeSolution exchange(int total, Map<Integer, String> moneyValues) {
        List<Integer> moneyKinds = new ArrayList<>(moneyValues.keySet());
        ExchangeSolution[] exchangeSolutions = new ExchangeSolution[total + 1];
        exchangeSolutions[0] = new ExchangeSolution(new HashMap<>(), 0);
        for (int currentTotal = 1; currentTotal < exchangeSolutions.length; currentTotal++) {
            int minMoneyCount = Integer.MAX_VALUE;
            int optimizeKind = 0;
            for (int index = 0; index < moneyKinds.size(); index++) {
                if (currentTotal >= moneyKinds.get(index)) {
                    if (minMoneyCount > exchangeSolutions[currentTotal - moneyKinds.get(index)].currenciesCount) {
                        minMoneyCount = exchangeSolutions[currentTotal - moneyKinds.get(index)].currenciesCount;
                        optimizeKind = index;
                    }
                }
            }
            exchangeSolutions[currentTotal] = new ExchangeSolution(null, 0);
            exchangeSolutions[currentTotal].currenciesCount = minMoneyCount + 1;
            exchangeSolutions[currentTotal].details = (HashMap<String, Integer>) exchangeSolutions[currentTotal - moneyKinds.get(optimizeKind)].details.clone();
            exchangeSolutions[currentTotal].details.computeIfPresent(moneyValues.get(moneyKinds.get(optimizeKind)), (key, value) -> ++value);
            exchangeSolutions[currentTotal].details.putIfAbsent(moneyValues.get(moneyKinds.get(optimizeKind)), 1);
        }
        return exchangeSolutions[total];
    }
}
