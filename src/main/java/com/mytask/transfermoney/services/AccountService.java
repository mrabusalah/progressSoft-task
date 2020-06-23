package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllClients() {
        return accountRepository.findAll();
    }

    // =============================Get Account By Id=============================

    public Optional<Account> getAccountById(Long id) {
        throwIfNullId(id);
        throwIfNegativeId(id);
        throwIfNotFoundId(id);

        return accountRepository.findById(id);
    }

    // =============================Get Account By Username=============================

    public Account getAccountByUsername(String username) {
        throwIfNullUsername(username);
        throwIfUsernameNotFound(username);

        return accountRepository.findAccountByClientUsername(username);
    }

    // =============================Save New Account=============================

    public Account saveNewAccount(Account account) {
        throwIfNullAccount(account);
        throwIfUsernameTaken(account);
        throwIfNullBalance(account);
        throwIfInvalidBalance(account);
        throwIfNullName(account);

        account.setClientPassword(new BCryptPasswordEncoder().encode(account.getClientPassword()));
        account.setClientBalance(Double.parseDouble(new DecimalFormat("#.000").format(account.getClientBalance())));
        account.setClientProfilePic(
                Objects.isNull(account.getClientProfilePic()) ?
                        "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png"
                        : account.getClientProfilePic());
        return accountRepository.save(account);
    }

    // =============================Update Exist Account=============================

    public Account updateExistAccount(Long id, Account account) {
        throwIfNullId(id);
        throwIfNullAccount(account);
        throwIfNotFoundId(id);
        // TODO make change password separated
        // account.setClientPassword(new BCryptPasswordEncoder().encode(account.getClientPassword()));
        return accountRepository.save(account);
    }

    // =============================Remove Account By Id=============================

    public void removeAccountById(Long id) {
        throwIfNullId(id);
        throwIfNotFoundId(id);

        accountRepository.deleteById(id);
    }

    // =============================Transfer money=============================

    @Transactional
    public void transferMoney(Long sender, Long receiver, Double amount) {
        throwIfSenderIdOrReceiverIdNull(sender, receiver);
        throwIfInvalidSenderIdOrReceiverId(sender, receiver);
        throwIfNullAmount(amount);
        throwIfInvalidAmount(amount);

        Account senderAccount = getAccountById(sender).get();
        Account receiverAccount = getAccountById(receiver).get();

        throwIfAmountNotAvailable(amount, senderAccount);

        double senderBalance = senderAccount.getClientBalance();
        double receiverBalance = receiverAccount.getClientBalance();

        senderAccount.setClientBalance(Double.parseDouble(new DecimalFormat("#.000")
                .format(senderBalance - amount)));
        receiverAccount.setClientBalance(Double.parseDouble(new DecimalFormat("#.000")
                .format(receiverBalance + amount)));

        List<Account> res = Arrays.asList(senderAccount, receiverAccount);
        accountRepository.saveAll(res);
    }


/*  [#]==========================================================================================================
    [#]
    [#]              ___           ___                       ___           ___          _____          ___
    [#]             /__/\         /  /\          ___        /__/\         /  /\        /  /::\        /  /\
    [#]            |  |::\       /  /:/_        /  /\       \  \:\       /  /::\      /  /:/\:\      /  /:/_
    [#]            |  |:|:\     /  /:/ /\      /  /:/        \__\:\     /  /:/\:\    /  /:/  \:\    /  /:/ /\
    [#]          __|__|:|\:\   /  /:/ /:/_    /  /:/     ___ /  /::\   /  /:/  \:\  /__/:/ \__\:|  /  /:/ /::\
    [#]         /__/::::| \:\ /__/:/ /:/ /\  /  /::\    /__/\  /:/\:\ /__/:/ \__\:\ \  \:\ /  /:/ /__/:/ /:/\:\
    [#]         \  \:\~~\__\/ \  \:\/:/ /:/ /__/:/\:\   \  \:\/:/__\/ \  \:\ /  /:/  \  \:\  /:/  \  \:\/:/~/:/
    [#]          \  \:\        \  \::/ /:/  \__\/  \:\   \  \::/       \  \:\  /:/    \  \:\/:/    \  \::/ /:/
    [#]           \  \:\        \  \:\/:/        \  \:\   \  \:\        \  \:\/:/      \  \::/      \__\/ /:/
    [#]            \  \:\        \  \::/          \__\/    \  \:\        \  \::/        \__\/         /__/:/
    [#]             \__\/         \__\/                     \__\/         \__\/                       \__\/
    [#]
    [#]========================================================================================================== */

    private void throwIfNotFoundId(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new IllegalArgumentException("id not found");
        }
    }

    private void throwIfNegativeId(Long id) {
        if (id < 0L) {
            throw new IllegalArgumentException("id is negative");
        }
    }

    private void throwIfNullId(Long id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("id is null");
        }
    }

    private void throwIfNullAccount(Account account) {
        if (Objects.isNull(account)) {
            throw new NullPointerException("account is null");
        }
    }

    private void throwIfUsernameNotFound(String username) {
        if (!accountRepository.existsAccountByClientUsername(username)) {
            throw new IllegalArgumentException("username not found");
        }
    }

    private void throwIfNullUsername(String username) {
        if (Objects.isNull(username)) {
            throw new NullPointerException("username is null");
        }
    }

    private void throwIfNullName(Account account) {
        if (Objects.isNull(account.getClientName())) {
            throw new NullPointerException("name is null");
        }
    }

    private void throwIfNullBalance(Account account) {
        if (Objects.isNull(account.getClientBalance())) {
            throw new NullPointerException("balance is null");
        }
    }

    private void throwIfInvalidBalance(Account account) {
        if (account.getClientBalance() <= 0) {
            throw new IllegalArgumentException("balance can't be less than or equal zero");
        }
    }

    private void throwIfUsernameTaken(Account account) {
        if (accountRepository.existsAccountByClientUsername(account.getClientUsername())) {
            throw new IllegalArgumentException("username already taken");
        }
    }

    private void throwIfSenderIdOrReceiverIdNull(Long sender, Long receiver) {
        if (Objects.isNull(sender) || Objects.isNull(receiver)) {
            throw new NullPointerException((Objects.isNull(sender) ? "sender " : "receiver ") + "id is null");
        }
    }

    private void throwIfInvalidSenderIdOrReceiverId(Long sender, Long receiver) {
        if (!accountRepository.existsById(sender) || !accountRepository.existsById(receiver)) {
            throw new IllegalArgumentException((!accountRepository.existsById(sender) ? "sender " : "receiver ") + "id not found");
        }
    }

    private void throwIfInvalidAmount(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("invalid amount");
        }
    }

    private void throwIfNullAmount(Double amount) {
        if (Objects.isNull(amount)) {
            throw new NullPointerException("amount is null");
        }
    }

    private void throwIfAmountNotAvailable(Double amount, Account senderAccount) {
        if (senderAccount.getClientBalance() < amount) {
            throw new IllegalArgumentException("amount not available");
        }
    }

}
