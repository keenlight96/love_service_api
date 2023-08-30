package com.service;

import com.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    List<Account> getAll();
    Account getById(long id);
    Account create(Account account);
    Account edit(Account account);
    void deleteById (long id);
}
