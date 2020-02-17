package com.mytask.transfermoney.AccountTest;

import com.mytask.transfermoney.Account;
import com.mytask.transfermoney.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest {

    private static final Long ID = 1L;
    private Account account;
    private FakeAccountRepository fakeAccountRepository;

    @BeforeEach
    void setUp() {
        fakeAccountRepository = new FakeAccountRepository();
        account = new Account(1L, 1.0, "client", "0123456798", "location", "");
        fakeAccountRepository.accounts.put(ID, account);
    }

    @Test
    public void SaveTest() {
        Account actual = fakeAccountRepository.accounts.put(account.getClientNumber(), account);
        assertThat(actual).isEqualTo(account);
    }

    @Test
    public void GetTest() {
        assertThat(fakeAccountRepository.accounts.get(ID)).isEqualTo(account);
    }

    @Test
    public void DeleteTest() {
        fakeAccountRepository.accounts.remove(ID);
        assertThat(fakeAccountRepository.accounts.containsKey(ID)).isFalse();
    }

    @Test
    public void GetAllTest() {
        assertThat(fakeAccountRepository.accounts.size())
                .isOne();
        assertThat(fakeAccountRepository.accounts.values())
                .containsExactly(account);
    }

    @Test
    public void UpdateTest() {
        Account actual = account;
        actual.setClientBalance(2.0);
        fakeAccountRepository.accounts.put(ID, actual);
        assertThat(fakeAccountRepository.accounts.get(ID)).isEqualTo(actual);
    }

    @Test
    public void TransferTest() {
        Account senderAccount = new Account();
        senderAccount.setClientBalance(100.0);
        Account receiverAccount = new Account();
        receiverAccount.setClientBalance(100.0);
        Double amount = 50.0;
        senderAccount.setClientBalance(senderAccount.getClientBalance() - amount);
        receiverAccount.setClientBalance(receiverAccount.getClientBalance() + amount);
        assertThat(senderAccount.getClientBalance() + amount).isEqualTo(receiverAccount.getClientBalance() - amount);
    }

    private static class FakeAccountRepository implements AccountRepository {

        private Map<Long, Account> accounts = new HashMap<>();

        @Override
        public <S extends Account> S save(S s) {
            return null;
        }

        @Override
        public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
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
        public Iterable<Account> findAll() {
            return null;
        }

        @Override
        public Iterable<Account> findAllById(Iterable<Long> iterable) {
            return null;
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
    }
}
