package com.mytask.transfermoney.services;

import com.mytask.transfermoney.module.Transaction;
import com.mytask.transfermoney.repositories.AccountRepository;
import com.mytask.transfermoney.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Long sender, Long receiver, double amount) {
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

    public List<Transaction> getAllTransactionsByUserId(Long id) {
        return transactionRepository.findAllBySenderIdOrderByDateDesc(id);
    }

    public List<Transaction> getAllTransactionsByReceiverId(Long id) {
        return transactionRepository.findAllByReceiverIdOrderByDateDesc(id);
    }

    public List<Transaction> getAllTransactionsById(Long id) {
        return transactionRepository.findAllBySenderIdOrReceiverIdOrderByDateDesc(id, id);
    }

    public Page<Transaction> page(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }
}
