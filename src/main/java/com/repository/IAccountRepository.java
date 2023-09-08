package com.repository;

import com.model.Account;
import com.model.dto.AccountDTO;
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

//    @Query(nativeQuery = true ,value = "select new com.model.dto.AccountDTO(ac.id, ac.username, ac.nickname, ac.avatar, ac.role_id, ac.status_id, ac.is_active) " +
//            "from Account ac " +
//            "join (select m.sender_id as ac2Id from Message m where m.receiver_id = :accId " +
//            "union select m.receiver_id as ac2Id from Message m where m.sender_id = :accId) as ac2 " +
//            "on ac.id = ac2.id " +
//            "join Role r ")
//    List<AccountDTO> getAllMessageReceiversByAccountId(@Param("accId") long id);

//    @Query(nativeQuery = true ,value = "select new com.model.dto.AccountDTO(ac.id, ac.username, ac.nickname, ac.avatar, ac.role_id, ac.status_id, ac.is_active) " +
//            "from Account ac ")
//    List<AccountDTO> getAllMessageReceiversByAccountId(@Param("accId") long id);

    @Query(nativeQuery = true ,value = "select new com.model.dto.AccountDTO(ac.id, ac.username, ac.nickname, ac.avatar, ac.role_id, ac.status_id, ac.is_active) " +
            "from Account ac ")
    List<AccountDTO> getAllMessageReceiversByAccountId(@Param("accId") long id);
}
