package com.mytask.transfermoney;

import com.mytask.transfermoney.module.Currency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class TransferMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferMoneyApplication.class, args);
    }
}
