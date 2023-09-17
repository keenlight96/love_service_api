package com.repository;

import com.model.Account;
import com.model.dto.AccountDTO;
import com.model.dto.AccountMessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    @Query("select ac from Account ac where ac.username = :username and ac.isActive = true")
    Account findActiveByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> getAccountByUsernameAndPassword(String username,String password);
    Account findAccountByEmail(String email);

    @Query(value = "select new com.model.dto.AccountMessageDTO(ac.id, ac.username, ac.nickname, ac.avatar, ac.role, ac.status, ac.isActive) " +
            "from Account ac " +
            "where (ac.id in (select m.receiver.id from Message m where m.type = 'private' and m.sender.id = :accId) " +
            "or ac.id in (select m.sender.id from Message m where m.type = 'private' and m.receiver.id = :accId))")
    List<AccountMessageDTO> getAllMessageReceiversByAccountId(@Param("accId") long id);
    @Query(value = "select a from Account a join Status s on a.status.id = s.id join Role r on a.role.id = r.id where " +
            "r.nameRole = 'ROLE_USER' and s.nameStatus = 'active' and  a.isActive = true order by a.id desc ")
    List<Account> getAllUserAc();

    @Query(value = "select a from Account a join Status s on a.status.id = s.id join Role r on a.role.id = r.id where " +
            "r.nameRole = 'ROLE_CCDV' and s.nameStatus = 'active' and  a.isActive = true order by a.id desc ")
    List<Account> getAllCCDVAc();

    @Query(value = "select new com.model.dto.AccountDTO(a, u )" +
            "from Account a join UserProfile u on a.id = u.account.id join Status s on a.status.id = s.id " +
            "join Role r on a.role.id = r.id " +
            "where (a.isActive = true) and (u.isActive =true )" +
            "and (a.role.id = 2)" +
            "and (:usernameParam is null or a.username like :usernameParam)" +
            "and (:statusParam is null or a.status.nameStatus = :statusParam)" +
            "order by a.id desc")
    List<AccountDTO> getAllAccountUserFilter(@Param("statusParam") String status, @Param("usernameParam") String username);

    @Query(value = "select new com.model.dto.AccountDTO(a,u)" +
            "from Account a join UserProfile u on a.id = u.account.id join Status s on a.status.id = s.id " +
            "join Role r on a.role.id = r.id " +
            "where (a.isActive = true) and (u.isActive =true )" +
            "and (a.role.id = 3)" +
            "and (:usernameParam is null or a.username like :usernameParam)" +
            "and (:statusParam is null or a.status.nameStatus = :statusParam)" +
            "order by a.id desc")
    List<AccountDTO> getAllAccountCCDVFilter(@Param("statusParam") String status, @Param("usernameParam") String username);

    @Query(value = "select a from Account a join Status s on a.status.id = s.id join Role r on a.role.id = r.id" +
            " where s.nameStatus = 'register' and r.nameRole = 'ROLE_CCDV' order by a.id desc ")
    List<Account> getAccountCCDVRegister();
}
