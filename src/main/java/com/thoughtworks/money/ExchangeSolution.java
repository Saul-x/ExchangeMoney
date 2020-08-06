package com.thoughtworks.money;

import java.util.HashMap;

public class ExchangeSolution {
    int total;
    HashMap<String, Integer> details;

    public ExchangeSolution(int total, HashMap<String, Integer> details) {
        this.total = total;
        this.details = details;
    }

    public HashMap<String, Integer> getDetails() {
        return details;
    }

    public int currenciesCount() {
        return details.values().stream().reduce(Math::addExact).orElse(0);
    }
}
