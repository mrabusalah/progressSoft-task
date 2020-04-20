package com.mytask.transfermoney.module;

import com.posadskiy.currencyconverter.CurrencyConverter;

import static com.posadskiy.currencyconverter.Currency.USD;

public class Currency {
    private static final String API_KEY = "6f954d058dd519e498e9";

    public Double getRate() {

        CurrencyConverter converter = new CurrencyConverter(API_KEY);

        return converter.rateFromJordanDinar(USD);
    }
}
