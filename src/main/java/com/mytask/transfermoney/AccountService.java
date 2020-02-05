package com.mytask.transfermoney;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllClients() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account addNewAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void removeAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public String transferMoney(Long sender, Long receiver, Double amount, String description) {
        Account senderAcc = getAccount(sender);
        Account receiverAcc = getAccount(receiver);

        Double senderAmount = senderAcc.getClientBalance();
        Double receiverAmount = receiverAcc.getClientBalance();

        senderAcc.setClientBalance(senderAmount - amount);
        receiverAcc.setClientBalance(receiverAmount + amount);

        updateAccount(senderAcc);
        updateAccount(receiverAcc);

        return "DONE\n" + description;
    }
}
