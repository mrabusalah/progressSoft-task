package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    public Double exchangeMoney(String from, String to) {
        Currency currency = new Currency();
        return currency.getRate(from, to);
    }
}
