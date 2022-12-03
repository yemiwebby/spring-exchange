package com.example.exchangeRates.controller;

import com.example.exchangeRates.model.Rate;
import com.example.exchangeRates.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rates")
public class ExchangeRateController {
    @Autowired
    ExchangeRatesService ratesService;

    @GetMapping(value = "")
    public List<Map<String, Rate[]>> getExchangeRates(HttpServletResponse response) {
        try {
            return ratesService.getExchangeRates();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/{currency}")
    public Map<String, Rate[]> getExchangeRatesForCurrency(@PathVariable("currency") String currency, HttpServletResponse response) {
        try {
            return ratesService.getExchangeRatesForCurrency(currency);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
