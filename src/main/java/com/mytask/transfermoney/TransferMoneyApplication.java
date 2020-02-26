package com.mytask.transfermoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2

public class TransferMoneyApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TransferMoneyApplication.class, args);
    }
}