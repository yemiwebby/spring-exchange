package com.example.exchangeRates.model;

public class Rate {

    private final String currency;
    private final double rate;

    public Rate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public double getRate() {
        return this.rate;
    }
}
