package com.service;

import com.model.Account;
import com.model.dto.AccountDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAccountService extends UserDetailsService {
    List<Account> getAll();
    Account getById(long id);
    Account create(Account account);
    Account edit(Account account);
    void deleteById (long id);
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> login(String username, String password);

    List<AccountDTO> getAllMessageReceiversByAccountId(long id);
}
