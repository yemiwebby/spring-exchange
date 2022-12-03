package com.example.exchangeRates.controller;

import com.example.exchangeRates.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    ExchangeRatesService ratesService;

    @GetMapping(value = "")
    public String[] getSupportedCurrencies() {
        return ratesService.getSupportedCurrencies();
    }
}
