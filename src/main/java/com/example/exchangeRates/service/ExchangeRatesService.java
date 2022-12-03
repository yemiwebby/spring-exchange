package com.example.exchangeRates.service;

import com.example.exchangeRates.model.Rate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExchangeRatesService {
    final String[] supportedCurrencies = {"EUR", "GBP", "NGN", "USD", "YEN", "CFA", "PES"};

    public List<Map<String, Rate[]>> getExchangeRates() throws Exception {
        List<Map<String, Rate[]>> rates = new ArrayList<>();
        for (String currency : this.supportedCurrencies) {
            rates.add(this.getExchangeRatesForCurrency(currency));
        }
        return rates;
    }

    public Map<String, Rate[]> getExchangeRatesForCurrency(String currency) throws Exception {
        Map<String, Rate[]> rates = new HashMap<>();
        rates.put(currency, this.getRatesForCurrency(currency));
        return rates;
    }

    private Rate[] getRatesForCurrency(String currency) throws Exception {
        List<Rate> rates = new ArrayList<>();
        for (String availableCurrency : this.getAvailableCurrencies(currency)) {
            rates.add(new Rate(availableCurrency, Math.random() * 100));
        }
        return rates.toArray(new Rate[0]);
    }

    private String[] getAvailableCurrencies(String currency) throws Exception {
        int currencyIndex = Arrays.asList(supportedCurrencies).indexOf(currency);
        if (currencyIndex == -1) {
            throw new Exception("Unsupported currency provided");
        }

        List<String> availableCurrencies = new ArrayList<>(Arrays.asList(this.supportedCurrencies));
        availableCurrencies.remove(currencyIndex);
        return availableCurrencies.toArray(new String[0]);
    }

    public String[] getSupportedCurrencies() {
        return this.supportedCurrencies;
    }
}
