package com.thoughtworks.money;

import java.util.HashMap;

public class ExchangeSolution {
    HashMap<String, Integer> details;
    int currenciesCount;
    public ExchangeSolution(HashMap<String, Integer> details, int currenciesCount) {
        this.details = details;
        this.currenciesCount = currenciesCount;
    }
}
