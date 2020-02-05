package com.mytask.transfermoney;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super(id + "");
    }
}
