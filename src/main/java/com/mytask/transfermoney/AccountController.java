package com.mytask.transfermoney;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @GetMapping("/clients/all-clients")
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

    @PostMapping("/clients/create-client")
    public Account saveNewAccount(@Valid @RequestBody Account account) {
        account.setClientPassword(new BCryptPasswordEncoder().encode(account.getClientPassword()));
        return accountService.saveNewAccount(account);
    }


    @PutMapping("/clients/update-client/{id}")
    public Account updateExistAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        account.setClientPassword(new BCryptPasswordEncoder().encode(account.getClientPassword()));
        return accountService.updateExistAccount(id, account);
    }

    @DeleteMapping("/clients/delete-client/{id}")
    public void removeAccountById(@PathVariable Long id) {
        accountService.removeAccountById(id);
    }

    @PostMapping("/transfer/{sender}/{receiver}/{amount}")
    public void transferMoney(@PathVariable Long sender, @PathVariable Long receiver, @PathVariable Double amount, @RequestBody String amountBody) {
        accountService.transferMoney(sender, receiver, amount);
    }

}
