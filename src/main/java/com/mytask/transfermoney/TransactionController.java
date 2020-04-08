package com.mytask.transfermoney;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/transactions/create-transaction")
    public Transaction addTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }
}
