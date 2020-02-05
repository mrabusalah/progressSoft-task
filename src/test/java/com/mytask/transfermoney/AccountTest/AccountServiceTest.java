package com.mytask.transfermoney.AccountTest;

import com.mytask.transfermoney.Account;
import com.mytask.transfermoney.AccountRepository;
import com.mytask.transfermoney.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest {

    private static final Long ID = 1L;
    private Account account;
    private AccountService accountService;
    private FakeAccountRepository fakeAccountRepository;

    @BeforeEach
    void setUp() {
        fakeAccountRepository = new FakeAccountRepository();
        account = new Account(1L, 1.0, "client", "0123456798", "location", "");
        accountService = new AccountService(fakeAccountRepository);
        fakeAccountRepository.accounts.put(ID, account);
    }

    @Test
    public void SaveTest() {
        Account actual = accountService.addNewAccount(account);
        assertThat(actual).isEqualTo(account);
    }

    @Test
    public void GetTest() {
        assertThat(accountService.getAccount(ID)).isEqualTo(account);
    }

    @Test
    public void DeleteTest() {
        accountService.removeAccount(ID);
        assertThat(fakeAccountRepository.accounts.containsKey(ID)).isFalse();
    }

    @Test
    public void GetAllTest() {
        assertThat(accountService.getAllClients())
                .size().isOne();
        assertThat(accountService.getAllClients())
                .containsExactly(account);
    }

    @Test
    public void UpdateTest() {
        if (!fakeAccountRepository.accounts.containsKey(ID)) {
            SaveTest();
            return;
        }
        assertThat(ID).isEqualTo(account.getClientNumber());
//        account.setClientBalance(2.0);
        fakeAccountRepository.accounts.put(ID, account);
    }

    @Test
    public void TransferTest() {
        Account senderAccount = new Account();
        senderAccount.setClientBalance(100.0);
        Account receiverAccount = new Account();
        receiverAccount.setClientBalance(100.0);
        Double amount = 50.0;
        senderAccount.setClientBalance(senderAccount.getClientBalance()-amount);
        receiverAccount.setClientBalance(receiverAccount.getClientBalance()+amount);
        assertThat(senderAccount.getClientBalance() + amount).isEqualTo(receiverAccount.getClientBalance() - amount);

    }

    private static class FakeAccountRepository implements AccountRepository {

        private Map<Long, Account> accounts = new HashMap<>();

        @Override
        public <S extends Account> S save(S account) {
            accounts.put(account.getClientNumber(), account);
            return account;
        }

        @Override
        public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Account> findById(Long aLong) {
            if (!accounts.containsKey(aLong)) {
                return Optional.empty();
            }
            return Optional.ofNullable(accounts.get(aLong));
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<Account> findAll() {
            return accounts.values();
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
            accounts.remove(aLong);
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
