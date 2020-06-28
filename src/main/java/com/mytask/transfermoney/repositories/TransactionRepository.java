package com.mytask.transfermoney.repositories;

import com.mytask.transfermoney.module.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySenderIdOrderByDateDesc(Long id);

    List<Transaction> findAllByReceiverIdOrderByDateDesc(Long id);

    List<Transaction> findAllBySenderIdOrReceiverIdOrderByDateDesc(Long senderId, Long receiverId);

    boolean existsBySenderId(Long id);

    boolean existsByReceiverId(Long id);

}
