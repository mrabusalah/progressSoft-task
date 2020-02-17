package com.mytask.transfermoney;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String addNewAccount(Account account) {
        if (!accountRepository.existsById(account.getClientNumber())) {
            accountRepository.save(account);
            return "Done , code = 200 \nNew Account Added Successfully\n" + account.toString();
        }
        throw AccountException.alreadyExist(account.getClientNumber());
    }

    public String getAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.findById(id);
            return "Done , code = 200 \nThe Account Information is \n" +
                    accountRepository.findById(id).toString();
        }
        throw AccountException.notFound(id);
    }

    public String updateAccount(Account account) {
        if (accountRepository.existsById(account.getClientNumber())) {
            accountRepository.save(account);
            return "Done , code = 200\nThe New Account Information is\n" +
                    account.toString();
        }
        throw AccountException.notFound(account.getClientNumber());
    }

    public String removeAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return "Done , code = 200\nThe Account { " + id + " } was deleted";
        }
        throw AccountException.notFound(id);
    }

    public String transferMoney(Long sender, Long receiver, Double amount, String description) {
        if (accountRepository.existsById(sender) && accountRepository.existsById(receiver)) {

            Optional<Account> senderAcc = accountRepository.findById(sender);
            Optional<Account> receiverAcc = accountRepository.findById(receiver);
            Double senderAmount = senderAcc.get().getClientBalance();
            Double receiverAmount = receiverAcc.get().getClientBalance();
            try {
                senderAcc.get().setClientBalance(senderAmount - amount);
                receiverAcc.get().setClientBalance(receiverAmount + amount);

                updateAccount(senderAcc.get());
                updateAccount(receiverAcc.get());

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            return "DONE\n" + description;
        } else {
            Long wrongID;
            if (accountRepository.existsById(sender)) wrongID = receiver;
            else wrongID = sender;
            throw AccountException.notFound(wrongID);
        }
    }
}
