package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.repositories.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<Account> getAllClients() {
        return (List<Account>) accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByClientUsername(username);
    }

    public Account saveNewAccount(Account account) {
        account.setClientPassword(new BCryptPasswordEncoder().encode(account.getClientPassword()));
        account.setClientBalance(Double.parseDouble(new DecimalFormat("#.000").format(account.getClientBalance())));
        return accountRepository.save(account);
    }

    public Account updateExistAccount(Long id, Account account) {
        if (accountRepository.existsById(id)) {
            return accountRepository.save(account);
        }
        throw new NullPointerException("Id not found");
    }

    public void removeAccountById(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new NullPointerException("Id not found");
        }
    }

    public void transferMoney(Long sender, Long receiver, Double amount) {
        if (accountRepository.existsById(sender) &&
                accountRepository.existsById(receiver)) {

            Optional<Account> senderAccount = getAccountById(sender);
            Optional<Account> receiverAccount = getAccountById(receiver);
            if (senderAccount.get().getClientBalance() >= amount) {

                DecimalFormat numberFormat = new DecimalFormat("#.000");

                senderAccount.get().setClientBalance(Double.parseDouble(numberFormat.format(senderAccount.get().getClientBalance() - amount)));
                receiverAccount.get().setClientBalance(Double.parseDouble(numberFormat.format(receiverAccount.get().getClientBalance() + amount)));

                System.out.println("request arrived here");

                accountRepository.save(senderAccount.get());
                accountRepository.save(receiverAccount.get());

            } else {
                throw new RuntimeException("Balance not available in account");
            }
        } else {
            throw new RuntimeException("Id not found");
        }
    }
}