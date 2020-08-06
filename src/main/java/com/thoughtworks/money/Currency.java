package com.thoughtworks.money;

public enum Currency {
    JPY("日元", 1),
    HKD("港币", JPY.value * 15),
    CNY("人民币", HKD.value * 2),
    USD("美元", CNY.value * 7),
    EUR("欧元", USD.value * 2);

    String name;
    int value;

    Currency(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
