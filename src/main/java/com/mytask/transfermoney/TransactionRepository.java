package com.mytask.transfermoney;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySenderIdOrderByDate(Long id);

    List<Transaction> findAllByReceiverIdOrderByDate(Long id);

    List<Transaction> findAllBySenderIdOrReceiverIdOrderByDateDesc(Long senderId, Long receiverId);
}
