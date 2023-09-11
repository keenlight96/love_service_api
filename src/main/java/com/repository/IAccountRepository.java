package com.repository;

import com.model.Account;
import com.model.dto.AccountMessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> getAccountByUsernameAndPassword(String username,String password);
    Account findAccountByEmail(String email);

    @Query(value = "select new com.model.dto.AccountMessageDTO(ac.id, ac.username, ac.nickname, ac.avatar, ac.role, ac.status, ac.isActive) " +
            "from Account ac " +
            "where (ac.id in (select m.receiver.id from Message m where m.sender.id = :accId) " +
            "or ac.id in (select m.sender.id from Message m where m.receiver.id = :accId))")
    List<AccountMessageDTO> getAllMessageReceiversByAccountId(@Param("accId") long id);

}
