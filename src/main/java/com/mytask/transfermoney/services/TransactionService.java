package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Transaction;
import com.mytask.transfermoney.repositories.AccountRepository;
import com.mytask.transfermoney.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Page<Transaction> page(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Page<Transaction> getAllTransactionsPage(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    //================================Get Transaction By Id================================

    public Optional<Transaction> getTransactionById(Long id) {
        throwIfNullId(id);
        throwIfInvalidId(id);

        return transactionRepository.findById(id);
    }

    //================================Add New Transaction================================

    public Transaction addTransaction(Long sender, Long receiver, Double amount) {
        throwIfNullSenderIdOrReceiverId(sender, receiver);
        throwIfInvalidSenderIdOrReceiverId(sender, receiver);
        throwIfSenderIdAndReceiverIdAreEqual(sender, receiver);
        throwIfNullAmount(amount);
        throwIfInvalidAmount(amount);

        Transaction transaction = new Transaction(sender, receiver, amount);
        return transactionRepository.save(transaction);
    }

    //================================Get Transactions By UserId================================

    public List<Transaction> getAllTransactionsBySenderId(Long id) {
        throwIfNullSenderId(id);
        throwIfInvalidSenderId(id);

        return transactionRepository.findAllBySenderIdOrderByDateDesc(id);
    }

    //================================Get Transaction By ReceiverId================================

    public List<Transaction> getAllTransactionsByReceiverId(Long id) {
        throwIfNullReceiverId(id);
        throwIfInvalidReceiverId(id);

        return transactionRepository.findAllByReceiverIdOrderByDateDesc(id);
    }

    //================================Get Transaction By ReceiverId================================

    public List<Transaction> getAllTransactionsById(Long id) {
        throwIfNullId(id);
        throwIfInvalidId(id);

        return transactionRepository.findAllBySenderIdOrReceiverIdOrderByDateDesc(id, id);
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


    private void throwIfNullId(Long id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("id is null");
        }
    }

    private void throwIfInvalidId(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new IllegalArgumentException("id not found");
        }
    }

    private void throwIfInvalidSenderIdOrReceiverId(Long sender, Long receiver) {
        if (!accountRepository.existsById(sender) || !accountRepository.existsById(receiver)) {
            throw new IllegalArgumentException((!accountRepository.existsById(sender) ? "sender " : "receiver ") + "id is invalid");
        }
    }

    private void throwIfNullSenderIdOrReceiverId(Long sender, Long receiver) {
        if (Objects.isNull(sender) || Objects.isNull(receiver)) {
            throw new NullPointerException((Objects.isNull(sender) ? "sender " : "receiver ") + "id is null");
        }
    }

    private void throwIfInvalidAmount(Double amount) {
        if (amount <= 0d) {
            throw new IllegalArgumentException("invalid amount");
        }
    }

    private void throwIfNullAmount(Double amount) {
        if (Objects.isNull(amount)) {
            throw new NullPointerException("amount is null");
        }
    }

    private void throwIfSenderIdAndReceiverIdAreEqual(Long sender, Long receiver) {
        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("sender id and receiver id are same");
        }
    }

    private void throwIfInvalidSenderId(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new IllegalArgumentException("invalid sender id");
        }
    }

    private void throwIfNullSenderId(Long id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("sender id is null");
        }
    }

    private void throwIfInvalidReceiverId(Long id) {
        if(!accountRepository.existsById(id)){
            throw new IllegalArgumentException("invalid receiver id");
        }
    }

    private void throwIfNullReceiverId(Long id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("receiver id is null");
        }
    }
}
