package com.mytask.transfermoney.services;


import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AccountServiceTest {
    Optional<Account> account1 = Optional.of(new Account(1L, "username1", "password", 100.0, "user2", "0123456789", "Amman", "user for test 1", "none"));
    Optional<Account> account2 = Optional.of(new Account(2L, "username2", "password", 100.0, "user2", "0123456789", "Amman", "user for test 2", "none"));

    Map<Long, Optional<Account>> accountMap = new HashMap<Long, Optional<Account>>() {
        {
            put(1L, account1);
            put(2L, account2);
        }
    };

    AccountService accountService = new AccountService(new AccountRepository() {

        @Override
        public Boolean existsAccountByClientUsername(String username) {
            int size = accountMap.size();
            for (long i = 1L; i < size; i++) {
                Optional<Account> account = accountMap.get(i);
                if (account.get().getClientUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Account findAccountByClientUsername(String username) {
            Account account = new Account();
            int size = accountMap.size();
            for (long i = 1L; i < size; i++) {
                Optional<Account> acc = accountMap.get(i);
                if (acc.get().getClientUsername().equals(username)) {
                    account = acc.get();
                }
            }
            return account;
        }

        @Override
        public List<Account> findAll() {
            return null;
        }

        @Override
        public List<Account> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Account> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public <S extends Account> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Account> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Account> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Account getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Account> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Account> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Account> S save(S s) {
            return null;
        }

        @Override
        public Optional<Account> findById(Long id) {
            return accountMap.get(id);
        }

        @Override
        public boolean existsById(Long id) {
            return accountMap.containsKey(id);
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Account account) {

        }

        @Override
        public void deleteAll(Iterable<? extends Account> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Account> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Account> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Account> boolean exists(Example<S> example) {
            return false;
        }
    });

    /*

        [#]=======================================================================================
        [#]    8888888 8888888888 8 8888888888     d888888o. 8888888 8888888888 d888888o.
        [#]          8 8888       8 8888         .`8888:' `88.     8 8888     .`8888:' `88.
        [#]          8 8888       8 8888         8.`8888.   Y8     8 8888     8.`8888.   Y8
        [#]          8 8888       8 8888         `8.`8888.         8 8888     `8.`8888.
        [#]          8 8888       8 888888888888  `8.`8888.        8 8888      `8.`8888.
        [#]          8 8888       8 8888           `8.`8888.       8 8888       `8.`8888.
        [#]          8 8888       8 8888            `8.`8888.      8 8888        `8.`8888.
        [#]          8 8888       8 8888        8b   `8.`8888.     8 8888    8b   `8.`8888.
        [#]          8 8888       8 8888        `8b.  ;8.`8888     8 8888    `8b.  ;8.`8888
        [#]          8 8888       8 888888888888 `Y8888P ,88P'     8 8888     `Y8888P ,88P'
        [#]=======================================================================================

    */

    @Test
    public void givenNullId_whenGetAccountById_thenThrowNullPointerException() {
        Long id = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.getAccountById(id));
        Assertions.assertEquals("Id is null", exception.getMessage());
    }

    @Test
    public void givenNegativeId_whenGetAccountById_thenThrowIllegalArgumentException() {
        Long id = -19L;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.getAccountById(id));
        Assertions.assertEquals("Id is negative", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenGetAccountById_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.getAccountById(id));
        Assertions.assertEquals("Id not found", exception.getMessage());
    }

    @Test
    public void givenValidId_whenGetAccountById_thenReturnAccount() {
        Long id = 1L;
        Account actual = new Account();
        actual.setId(id);
        actual.setClientUsername("username1");

        Optional<Account> expected = accountService.getAccountById(id);
        Assertions.assertEquals(expected.get().getClientUsername(),
                actual.getClientUsername());
    }


    @Test
    public void givenNullUsername_whenGetUsernameById_thenThrowNullPointerException() {
        String username = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.getAccountByUsername(username));
        Assertions.assertEquals("username is null", exception.getMessage());
    }

    @Test
    public void givenInvalidUsername_whenGetAccountByUsername_thenThrowIllegalArgumentException() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedUsername = new String(array, UTF_8);

        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.getAccountByUsername(generatedUsername));
        Assertions.assertEquals("username not found", exception.getMessage());
    }

    @Test
    public void givenValidUsername_whenGetAccountByUsername_thenReturnAccount() {
        String username = "username1";
        Account actual = new Account();
        actual.setClientUsername(username);

        Account expected = accountService.getAccountByUsername(username);
        Assertions.assertEquals(expected.getClientUsername(),
                actual.getClientUsername());
    }

}
