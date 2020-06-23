package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Transaction;
import com.mytask.transfermoney.repositories.AccountRepository;
import com.mytask.transfermoney.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
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

    public Transaction addTransaction(Long sender, Long receiver, double amount) {
        throwIfNullSenderIdOrRecreiverId(sender, receiver);
        throwIfInvalidSenderIdOrReceiverId(sender, receiver);

        // arrived here in testing 23-06-2020
        Transaction transaction = new Transaction(sender, receiver, amount);

        if (accountRepository.existsById(transaction.getSenderId())
                && accountRepository.existsById(transaction.getReceiverId())) {

            if (!(transaction.getReceiverId().equals(transaction.getSenderId())))
                return transactionRepository.save(transaction);
            else
                throw new NullPointerException("you cannot send money to your self");
        } else
            throw new NullPointerException("id not available");
    }

    private void throwIfInvalidSenderIdOrReceiverId(Long sender, Long receiver) {
        if (!transactionRepository.existsById(sender) || !transactionRepository.existsById(receiver)) {
            throw new IllegalArgumentException((!transactionRepository.existsById(sender) ? "sender " : "receiver ") + "id is invalid");
        }
    }

    private void throwIfNullSenderIdOrRecreiverId(Long sender, Long receiver) {
        if (Objects.isNull(sender) || Objects.isNull(receiver)) {
            throw new NullPointerException((Objects.isNull(sender) ? "sender " : "receiver ") + "id is null");
        }
    }

    //================================Get Transactions By UserId================================

    public List<Transaction> getAllTransactionsBySenderId(Long id) {
        return transactionRepository.findAllBySenderIdOrderByDateDesc(id);
    }

    //================================Get Transaction By ReceiverId================================

    public List<Transaction> getAllTransactionsByReceiverId(Long id) {
        return transactionRepository.findAllByReceiverIdOrderByDateDesc(id);
    }

    //================================Get Transaction By ReceiverId================================

    public List<Transaction> getAllTransactionsById(Long id) {
        return transactionRepository.findAllBySenderIdOrReceiverIdOrderByDateDesc(id, id);
    }

    public Page<Transaction> page(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    private void throwIfNullId(Long id) {
        if (Objects.isNull(id)) {
            throw new NullPointerException("id is null");
        }
    }

    private void throwIfInvalidId(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("id not found");
        }
    }
}
