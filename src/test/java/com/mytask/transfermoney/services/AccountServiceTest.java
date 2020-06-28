package com.mytask.transfermoney.services;


import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.repositories.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AccountServiceTest {
    Account account1 = (new Account(1L, "username1", "password", 100.0, "user2", "0123456789", "Amman", "user for test 1", "none"));
    Account account2 = (new Account(2L, "username2", "password", 100.0, "user2", "0123456789", "Amman", "user for test 2", "none"));

    Map<Long, Account> accountMap = new HashMap<Long, Account>() {
        {
            put(1L, account1);
            put(2L, account2);
        }
    };

    Long[] ids = {
            1L, // sender
            2L // receiver
    };

    double[] validAmounts = {
            0.6, 1, 34, 99, 20.47
    };

    double[] invalidAmounts = {
            0, -1, -2.8, -100.2973,
    };

    AccountService accountService = new AccountService(new AccountRepository() {

        @Override
        public Boolean existsAccountByClientUsername(String username) {
            int size = accountMap.size();
            for (long i = 1L; i < size; i++) {
                Account account = accountMap.get(i);
                if (account.getClientUsername().equals(username)) {
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
                Account acc = accountMap.get(i);
                if (acc.getClientUsername().equals(username)) {
                    account = acc;
                    break;
                }
            }
            return account;
        }

        @Override
        public @NotNull List<Account> findAll() {
            return null;
        }

        @Override
        public @NotNull List<Account> findAll(Sort sort) {
            return null;
        }

        @Override
        public @NotNull List<Account> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public <S extends Account> @NotNull List<S> saveAll(Iterable<S> iterable) {
            List<S> result = new ArrayList<S>();

            for (S entity : iterable) {
                result.add(save(entity));
            }
            return result;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Account> @NotNull S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Account> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public @NotNull Account getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Account> @NotNull List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Account> @NotNull List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public @NotNull Page<Account> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Account> @NotNull S save(S s) {
            if (existsById(s.getId())) {
                return (S) accountMap.put(s.getId(), s);
            }

            return (S) accountMap.put(Long.parseLong(String.valueOf(accountMap.size() + 1)), s);
        }

        @Override
        public @NotNull Optional<Account> findById(Long id) {
            return Optional.of(accountMap.get(id));
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
        public void deleteById(Long id) {
            accountMap.remove(id);
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
        public <S extends Account> @NotNull Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Account> @NotNull Page<S> findAll(Example<S> example, Pageable pageable) {
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
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @Test
    public void givenNegativeId_whenGetAccountById_thenThrowIllegalArgumentException() {
        Long id = -19L;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.getAccountById(id));
        Assertions.assertEquals("id is negative", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenGetAccountById_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.getAccountById(id));
        Assertions.assertEquals("id not found", exception.getMessage());
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

    @Test
    public void givenNullAccount_whenSaveNewAccount_thenThrowNullPointerException() {
        Account account = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.saveNewAccount(account));
        Assertions.assertEquals("account is null", exception.getMessage());
    }

    @Test
    public void givenAccountWithExistUsername_whenSaveNewAccount_thenThrowIllegalArgumentException() {
        Account account = new Account();
        account.setClientUsername("username1");
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.saveNewAccount(account));
        Assertions.assertEquals("username already taken", exception.getMessage());
    }

    @Test
    public void givenNullAccountBalance_whenSaveNewAccount_thenThrowNullPointerException() {
        Account account = new Account();
        account.setClientUsername("test1");
        account.setClientBalance(null);
        System.out.println(account.toString());
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.saveNewAccount(account));
        Assertions.assertEquals("balance is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenInvalidAccountBalance_whenSaveNewAccount_thenThrowIllegalArgumentException() {
        Account account = new Account();
        int bound = 10;
        double randomBalance = new Random().nextInt(bound + 1);
        account.setClientBalance(randomBalance == 0 ? 0 : randomBalance * -1);
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.saveNewAccount(account));
        Assertions.assertEquals("balance can't be less than or equal zero", exception.getMessage());
    }

    @Test
    public void givenNullName_whenSaveNewAccount_thenThrowNullPointerException() {
        Account account = new Account();
        account.setClientUsername("test1");
        account.setClientBalance(10.0);
        account.setClientName(null);

        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.saveNewAccount(account));
        Assertions.assertEquals("name is null", exception.getMessage());

    }

    @RepeatedTest(2)
    public void givenValidAccount_whenSaveNewAccount_thenStoreAccount() {
        Account account = new Account(3L, "testUsername", "password", 100.0, "name", "0123456789", "amman", "nothing", null);
        int sizeBefore = accountMap.size();
        accountService.saveNewAccount(account);
        int sizeAfter = accountMap.size();
        Assertions.assertNotEquals(sizeAfter, sizeBefore);
        Assertions.assertEquals(sizeAfter - 1, sizeBefore);
    }

    @Test
    public void givenNullId_whenUpdateExistAccount_thenThrowNullPointerException() {
        Account account = account1;
        Long id = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.updateExistAccount(id, account));
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenNullAccount_whenUpdateExistAccount_thenThrowNullPointerException() {
        int generatedId = new Random().nextInt(accountMap.size() + 1);
        Long id = (long) (generatedId == 0 ? 1 : generatedId);
        Account account = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.updateExistAccount(id, account));
        Assertions.assertEquals("account is null", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenUpdateExistAccount_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        Account account = account1;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.updateExistAccount(id, account));
        Assertions.assertEquals("id not found", exception.getMessage());
    }

    @Test
    public void givenValidIdAndValidAccount_whenUpdateExistAccount_thenUpdateAccount() {
        Long id = 1L;
        Account account = account1;
        String updatedClientName = "updated client name";
        account.setClientName(updatedClientName);
        accountService.updateExistAccount(id, account);
        Assertions.assertEquals(updatedClientName, account.getClientName());
    }

    @Test
    public void givenNullId_whenRemoveAccountById_thenThrowNullPointerException() {
        Long id = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.removeAccountById(id));
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenRemoveAccountById_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.removeAccountById(id));
        Assertions.assertEquals("id not found", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenValidId_whenRemoveAccountById_thenRemoveAccount() {
        int generatedId = new Random().nextInt(accountMap.size() + 1);
        Long id = (long) (generatedId == 0 ? 1 : generatedId);
        int sizeBefore = accountMap.size();
        accountService.removeAccountById(id);
        Assertions.assertNotEquals(accountMap.size(), sizeBefore);
        Assertions.assertEquals(accountMap.size() + 1, sizeBefore);
    }

    @RepeatedTest(5)
    public void givenNullSenderIdOrReceiverId_whenTransferMoney_thenThrowNullPointerException() {
        double randomAmount = 1 + (500 - 1) * new Random().nextDouble();
        int randomId = new Random().nextInt(ids.length);
        ids[randomId] = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.transferMoney(ids[0], ids[1], randomAmount));
        Assertions.assertEquals((Objects.isNull(ids[0]) ? "sender " : "receiver ") + "id is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenInvalidSenderIdOrReceiverId_whenTransferMoney_thenThrowIllegalArgumentException() {
        double randomAmount = 1 + (500 - 1) * new Random().nextDouble();
        int randomId = new Random().nextInt(ids.length);
        ids[randomId] = Long.MAX_VALUE;

        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney(ids[0], ids[1], randomAmount));
        Assertions.assertEquals((randomId == 0 ? "sender " : "receiver ") + "id not found", exception.getMessage());
    }

    @Test
    public void givenNullAmount_whenTransferMoney_thenThrowNullPointerException() {
        Double amount = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.transferMoney(ids[0], ids[1], amount));
        Assertions.assertEquals("amount is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenInvalidAmount_whenTransferMoney_thenThrowIllegalArgumentException() {

        int amountIdx = new Random().nextInt(invalidAmounts.length);
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney(ids[0], ids[1], invalidAmounts[amountIdx]));
        Assertions.assertEquals("invalid amount", exception.getMessage());
    }

    @Test
    public void givenUnavailableAmountInAccount_whenTransfer_thenThrowIllegalArgumentException() {
        double amount = Double.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney(ids[0], ids[1], amount));
        Assertions.assertEquals("amount not available", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenValidSenderIdAndReceiverAndAmount_whenTransferMoney_thenTransfer() {
        int randomIdx = new Random().nextInt(validAmounts.length);
        double amount = validAmounts[randomIdx];

        double senderBalance = accountService.getAccountById(ids[0])
                .get().getClientBalance();
        double receiverBalance = accountService.getAccountById(ids[1])
                .get().getClientBalance();

        accountService.transferMoney(ids[0], ids[1], amount);

        Assertions.assertNotEquals(accountService.getAccountById(ids[0])
                .get().getClientBalance(), senderBalance);
        Assertions.assertNotEquals(accountService.getAccountById(ids[1])
                .get().getClientBalance(), receiverBalance);

        Assertions.assertEquals(accountService.getAccountById(ids[0])
                .get().getClientBalance(), senderBalance - amount);
        Assertions.assertEquals(accountService.getAccountById(ids[1])
                .get().getClientBalance(), receiverBalance + amount);
    }

    @Test
    public void givenNullId_whenChangePassword_thenThrowNullPointerException() {
        Long id = null;
        String password = "password";
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.changePassword(id, password));
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @Test
    public void givenNullPassword_whenChangePassword_thenThrowNullPointerException() {
        Long id = 1L;
        String password = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> accountService.changePassword(id, password));
        Assertions.assertEquals("password is null", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenChangePassword_thenThrowNullPointerException() {
        Long id = Long.MAX_VALUE;
        String password = "password";
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> accountService.changePassword(id, password));
        Assertions.assertEquals("id is invalid", exception.getMessage());
    }

    @Test
    public void givenValidIdAndPassword_whenChangePassword_thenChangePassword() {
        Long id = 1L;
        String newPassword = "new-password";
        accountService.changePassword(id, newPassword);
        Assertions.assertNotEquals(newPassword, accountMap.get(id).getClientPassword());
    }
}
