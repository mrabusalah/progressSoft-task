package com.mytask.transfermoney.repositories;

import com.mytask.transfermoney.module.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySenderIdOrderByDate(Long id);

    List<Transaction> findAllByReceiverIdOrderByDate(Long id);

    List<Transaction> findAllBySenderIdOrReceiverIdOrderByDateDesc(Long senderId, Long receiverId);
}
