package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.module.Transaction;
import com.mytask.transfermoney.repositories.AccountRepository;
import com.mytask.transfermoney.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class TransactionServiceTest {

    Map<Long, Transaction> transactionMap = new HashMap<Long, Transaction>() {
        {
            put(1L, new Transaction(1L, 2L, 100));
            put(2L, new Transaction(2L, 1L, 50));
        }
    };
    Long[] ids = {
            1L, // sender
            2L // receiver
    };
    double[] validAmounts = {
            0.6, 1, 34, 99,
    };

    double[] invalidAmounts = {
            0, -1, -2.8, -100.2973,
    };
    TransactionService transactionService = new TransactionService(new TransactionRepository() {
        @Override
        public List<Transaction> findAllBySenderIdOrderByDateDesc(Long id) {
            List<Transaction> result = new ArrayList<>();
            int size = transactionMap.size();
            for (long i = 1; i <= size; i++) {
                if (transactionMap.get(i).getSenderId().equals(id)) {
                    result.add(transactionMap.get(i));
                }
            }
            return result;
        }

        @Override
        public List<Transaction> findAllByReceiverIdOrderByDateDesc(Long id) {
            List<Transaction> result = new ArrayList<>();
            int size = transactionMap.size();
            for (long i = 1; i <= size; i++) {
                if (transactionMap.get(i).getReceiverId().equals(id)) {
                    result.add(transactionMap.get(i));
                }
            }
            return result;
        }

        @Override
        public List<Transaction> findAllBySenderIdOrReceiverIdOrderByDateDesc(Long senderId, Long receiverId) {
            List<Transaction> result = new ArrayList<>();
            int size = transactionMap.size();
            for (long i = 1; i <= size; i++) {
                result.add(transactionMap.get(i));
            }
            return result;
        }

        @Override
        public List<Transaction> findAll() {
            return null;
        }

        @Override
        public List<Transaction> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Transaction> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public <S extends Transaction> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Transaction> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Transaction> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Transaction getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Transaction> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Transaction> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Transaction> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Transaction> S save(S s) {
            transactionMap.put(3L, s);
            return (S) transactionMap.get(3L);
        }

        @Override
        public Optional<Transaction> findById(Long id) {
            return Optional.of(transactionMap.get(id));
        }

        @Override
        public boolean existsById(Long id) {
            return transactionMap.containsKey(id);
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Transaction transaction) {

        }

        @Override
        public void deleteAll(Iterable<? extends Transaction> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Transaction> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Transaction> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Transaction> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Transaction> boolean exists(Example<S> example) {
            return false;
        }
    }, new AccountRepository() {
        @Override
        public Boolean existsAccountByClientUsername(String username) {
            return null;
        }

        @Override
        public Account findAccountByClientUsername(String username) {
            return null;
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
        public Optional<Account> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
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

    @Test
    public void givenNullId_whenGetTransactionById_thenThrowNullPointerException() {
        Long id = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.getTransactionById(id));
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenGetTransactionById_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.getTransactionById(id));
        Assertions.assertEquals("id not found", exception.getMessage());
    }

    @Test
    public void givenValidId_whenGetTransactionById_thenReturnTransaction() {
        Long id = 1L;
        Transaction transaction = transactionService.getTransactionById(id).get();
        Assertions.assertEquals(transaction, transactionMap.get(id));
    }

    @RepeatedTest(5)
    public void givenNullSenderIdOrReceiverId_whenAddNewTransaction_thenThrowNullPointerException() {
        int randomIdx = new Random().nextInt(ids.length);
        ids[randomIdx] = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.addTransaction(ids[0], ids[1], 10d));
        Assertions.assertEquals((randomIdx == 0 ? "sender " : "receiver ") + "id is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenInvalidSenderIdOrReceiver_whenAddNewTransaction_thenThrowIllegalArgumentException() {
        int randomIdx = new Random().nextInt(ids.length);
        ids[randomIdx] = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.addTransaction(ids[0], ids[1], 10d));
        Assertions.assertEquals((randomIdx == 0 ? "sender " : "receiver ") + "id is invalid", exception.getMessage());
    }

    @Test
    public void givenTheSameSenderIdAndReceiverId_whenAddNewTransaction_thenThrowIllegalArgumentException() {
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.addTransaction(ids[0], ids[0], 10d));
        Assertions.assertEquals("sender id and receiver id are same", exception.getMessage());
    }

    @Test
    public void givenNullAmount_whenAddNewTransaction_thenThrowNullPointerException() {
        Double amount = null;

        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.addTransaction(ids[0], ids[1], amount));
        Assertions.assertEquals("amount is null", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenInvalidAmount_whenAddNewTransaction_thenThrowIllegalArgumentException() {
        int randomIdx = new Random().nextInt(invalidAmounts.length);
        Double amount = invalidAmounts[randomIdx];
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.addTransaction(ids[0], ids[1], amount));
        Assertions.assertEquals("invalid amount", exception.getMessage());
    }

    @RepeatedTest(5)
    public void givenValidSenderIdAndReceiverIdAndAmount_whenAddNewTransaction_thenSaveTransaction() {
        int randomIdx = new Random().nextInt(validAmounts.length);
        Transaction actual = new Transaction(ids[0], ids[1], validAmounts[randomIdx]);
        int sizeBefore = transactionMap.size();
        Transaction expected = transactionService.addTransaction(actual.getSenderId(), actual.getReceiverId(), actual.getAmount());
        int sizeAfter = transactionMap.size();

        Assertions.assertNotEquals(sizeAfter, sizeBefore);
        Assertions.assertEquals(sizeAfter - 1, sizeBefore);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenNullSenderId_whenGetAllTransactionsBySenderId_thenThrowNullPointerException() {
        Long senderId = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.getAllTransactionsBySenderId(senderId));
        Assertions.assertEquals("sender id is null", exception.getMessage());
    }

    @Test
    public void givenInvalidSenderId_whenGetAllTransactionsBySenderId_thenThrowIllegalArgumentException() {
        Long senderId = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.getAllTransactionsBySenderId(senderId));
        Assertions.assertEquals("invalid sender id", exception.getMessage());
    }

    @Test
    public void givenValidSenderId_whenGetAllTransactionsBySenderId_thenReturnTransactions() {
        Long senderId = ids[0];
        List<Transaction> actual = Arrays.asList(transactionMap.get(senderId));
        List<Transaction> expected = transactionService.getAllTransactionsBySenderId(senderId);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void givenNullReceiverId_whenGetAllTransactionsByReceiverId_thenThrowNullPointerException() {
        Long receiverId = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.getAllTransactionsByReceiverId(receiverId));
        Assertions.assertEquals("receiver id is null", exception.getMessage());
    }

    @Test
    public void givenInvalidReceiverId_whenGetAllTransactionsByReceiverId_thenThrowIllegalArgumentException() {
        Long receiverId = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.getAllTransactionsByReceiverId(receiverId));
        Assertions.assertEquals("invalid receiver id", exception.getMessage());
    }

    @Test
    public void givenValidReceiverId_whenGetAllTransactionsByReceiverId_thenReturnTransactions() {
        Long receiverId = ids[1];
        List<Transaction> actual = new ArrayList<>();
        int size = transactionMap.size();
        for (long i = 1; i <= size; i++) {
            if (transactionMap.get(i).getReceiverId().equals(receiverId)) {
                actual.add(transactionMap.get(i));
            }
        }
        List<Transaction> expected = transactionService.getAllTransactionsByReceiverId(receiverId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenNullId_whenGetAllTransactionsById_thenThrowNullPointerException() {
        Long id = null;
        NullPointerException exception = Assertions
                .assertThrows(NullPointerException.class, () -> transactionService.getAllTransactionsById(id));
        Assertions.assertEquals("id is null", exception.getMessage());
    }

    @Test
    public void givenInvalidId_whenGetAllTransactionsById_thenThrowIllegalArgumentException() {
        Long id = Long.MAX_VALUE;
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> transactionService.getAllTransactionsById(id));
        Assertions.assertEquals("id not found", exception.getMessage());
    }

    @Test
    public void givenValidId_whenGetAllTransactionsById_thenReturnTransactions() {
        Long id = ids[0];
        List<Transaction> actual = new ArrayList<>();
        int size = transactionMap.size();
        for (long i = 1; i <= size; i++) {
            actual.add(transactionMap.get(i));
        }
        List<Transaction> expected = transactionService.getAllTransactionsById(id);
        Assertions.assertEquals(expected, actual);
    }
}
