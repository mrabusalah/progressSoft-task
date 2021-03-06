package com.mytask.transfermoney.controlles;

import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.services.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/clients")
    public List<Account> getAllAccounts() {
        return accountService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Optional<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/profile/{username}")
    public Account getAccountByUsername(@PathVariable String username) {
        return accountService.getAccountByUsername(username);
    }

    @PostMapping("/clients")
    public Account saveNewAccount(@Valid @RequestBody Account account) throws Exception {
        return accountService.saveNewAccount(account);
    }


    @PutMapping("/clients/{id}")
    public Account updateExistAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return accountService.updateExistAccount(id, account);
    }

    @DeleteMapping("/clients/{id}")
    public void removeAccountById(@PathVariable Long id) {
        accountService.removeAccountById(id);
    }

    @PostMapping("/transfer/{sender}/{receiver}/{amount}")
    public void transferMoney(@PathVariable Long sender, @PathVariable Long receiver, @PathVariable Double amount) {
        accountService.transferMoney(sender, receiver, amount);
    }

    @PutMapping("/change-password/{id}/{password}")
    public void changePassword(@PathVariable Long id, @PathVariable String password) {
        accountService.changePassword(id, password);
    }
}
