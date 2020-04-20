package com.mytask.transfermoney.repositories;

import com.mytask.transfermoney.module.Account;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Long> {

    Boolean existsAccountByClientUsername(String username);

    Account findAccountByClientUsername(String username);
}
