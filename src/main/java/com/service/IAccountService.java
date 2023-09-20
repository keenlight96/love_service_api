package com.service;

import com.model.Account;
import com.model.dto.AccountDTO;
import com.model.dto.AccountMessageDTO;
import com.model.dto.FilterAccountByStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
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
    Account findActiveByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> login(String username, String password);
    String workOrRest(long id);
    public Account activeAccount(String email);
    public String emailActive(String email) ;

//  Optional<Account> getByProviderUserId(long id);

    List<AccountMessageDTO> getAllMessageReceiversByAccountId(long id);
    List<Account> getAllUserAc( );
    String blockAccount(Long idAccount);
    List<Account> getAllCCDVAc( );
    List<AccountDTO> getAllAccountUserFilter(FilterAccountByStatusDTO filterAccountByStatusDTO);
    List<AccountDTO> getAllAccountCCDVFilter(FilterAccountByStatusDTO filterAccountByStatusDTO);
    List<Account> getAccountCCDVRegister();
    String unBlockAccount(String username);
}
