package com.mytask.transfermoney.auth0;


import com.mytask.transfermoney.module.Account;
import com.mytask.transfermoney.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("UserService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account user = accountRepository.findAccountByClientUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return user;
    }

}
