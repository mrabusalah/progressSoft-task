package com.mytask.transfermoney.module;

import com.posadskiy.currencyconverter.CurrencyConverter;

public class Currency {
    private static final String API_KEY = "6f954d058dd519e498e9";

    public Double getRate(String from, String to) {

        CurrencyConverter converter = new CurrencyConverter(API_KEY);

        return converter.rate(from, to);
    }
}
