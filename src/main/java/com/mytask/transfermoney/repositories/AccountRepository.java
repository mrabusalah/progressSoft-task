package com.mytask.transfermoney.repositories;

import com.mytask.transfermoney.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsAccountByClientUsername(String username);

    Account findAccountByClientUsername(String username);
}
