package com.mytask.transfermoney;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/client")
    public List<Account> getAllAccounts() {
        return accountService.getAllClients();
    }

    @RequestMapping("/client/{id}")
    public String getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client")
    public String addNewAccount(@Valid @RequestBody Account account) {
        return accountService.addNewAccount(account);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/client")
    public String updateAccount(@Valid @RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/{id}")
    public String removeAccount(@PathVariable Long id) {
        return accountService.removeAccount(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/client/transfer/{sender}/{receiver}/{amount}/{description}")
    public String transferMoney(@PathVariable Long sender, @PathVariable Long receiver, @PathVariable Double amount, @PathVariable String description) throws Exception {
        return accountService.transferMoney(sender, receiver, amount, description);
    }
}
