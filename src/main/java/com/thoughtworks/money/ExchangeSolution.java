package com.thoughtworks.money;

import java.util.HashMap;

public class ExchangeSolution {
    HashMap<String, Integer> details;
    public ExchangeSolution(HashMap<String, Integer> details) {
        this.details = details;
    }

    public int currenciesCount() {
        return details.values().stream().reduce(Math::addExact).orElse(0);
    }
}
