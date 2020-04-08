package com.mytask.transfermoney;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionsByUserId(Long id) {
        return transactionRepository.findAllBySenderIdOrderByDate(id);
    }

    public List<Transaction> getAllTransactionsByReceiverId(Long id) {
        return transactionRepository.findAllByReceiverIdOrderByDate(id);
    }

    public List<Transaction> getAllTransactionsById(Long id) {
        return transactionRepository.findAllBySenderIdOrReceiverIdOrderByDateDesc(id, id);
    }
}
