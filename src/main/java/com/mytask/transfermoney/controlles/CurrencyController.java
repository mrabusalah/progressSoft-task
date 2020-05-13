package com.mytask.transfermoney.controlles;

import com.mytask.transfermoney.services.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("convert-currency")
    public Double exchangeMoney(@RequestBody Map<String, String> currency) {
        return currencyService.exchangeMoney(currency.get("from"), currency.get("to"));
    }
}
