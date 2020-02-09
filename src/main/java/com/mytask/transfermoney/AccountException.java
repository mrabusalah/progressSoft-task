package com.mytask.transfermoney;

public class AccountException extends Exception {

    public static RuntimeException notFound(Long id) {
        return new RuntimeException("The User id = { " + id + " } Not Found");
    }

    public static RuntimeException alreadyExist(Long id) {
        return new RuntimeException("The User id = { " + id + " } is Already Exist");
    }

}
