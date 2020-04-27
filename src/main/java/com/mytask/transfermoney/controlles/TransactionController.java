package com.mytask.transfermoney.controlles;

import com.mytask.transfermoney.module.Transaction;
import com.mytask.transfermoney.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/all-transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/all-transactions/{id}")
    public List<Transaction> getAllTransactionsById(@PathVariable Long id) {
        return transactionService.getAllTransactionsById(id);
    }

    @GetMapping("/transactions/sender/{id}")
    public List<Transaction> getAllTransactionsBySenderId(@PathVariable Long id) {
        return transactionService.getAllTransactionsByUserId(id);
    }

    @GetMapping("/transactions/receiver/{id}")
    public List<Transaction> getAllTransactionsByReceiverId(@PathVariable Long id) {
        return transactionService.getAllTransactionsByReceiverId(id);
    }

    @GetMapping("/transactions/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/transactions/create-transaction/{sender}/{receiver}/{amount}")
    public Transaction addTransaction(@PathVariable Long sender, @PathVariable Long receiver, @PathVariable double amount) {
        return transactionService.addTransaction(sender, receiver, amount);
    }
}
