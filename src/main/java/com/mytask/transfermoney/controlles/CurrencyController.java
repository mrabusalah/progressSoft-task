package com.mytask.transfermoney.controlles;

import com.mytask.transfermoney.services.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("convert-currency/{from}/{to}/{amount}")
    public Double exchangeMoney(@PathVariable String from, @PathVariable String to, @PathVariable Double amount) {
        System.out.println(from + " " + to);
        return currencyService.exchangeMoney(from, to, amount);
    }
}
