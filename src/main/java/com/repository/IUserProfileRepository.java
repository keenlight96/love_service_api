package com.repository;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.AccountCCDVDTO;
import com.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    UserProfile getByAccount_Id(Long id);
    Optional<UserProfile> getUserProfileByAccount_Id(long id);
    @Query("SELECT new com.model.dto.AccountCCDVDTO(u, a, '', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
            "FROM UserProfile u " +
            "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
            "LEFT JOIN Account a ON a.id = u.account.id " +
            "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
            "WHERE (u.account.role.id = 3) " +
            "AND (u.account.status.id = 1) " +
            "AND (b.isActive = true) " +
            "AND (b.status.id = 6) " +
            "AND (u.account.isActive = true) " +
            "AND (rev.isActive = true OR rev IS NULL) " +
            "AND (u.gender = 'nam')" +
            "GROUP BY u.id " +
            "ORDER BY COUNT(DISTINCT b.id) DESC")
    List<AccountCCDVDTO> findTop4MaleAccountCCDV();
    @Query("SELECT new com.model.dto.AccountCCDVDTO(u, a, '', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
            "FROM UserProfile u " +
            "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
            "LEFT JOIN Account a ON a.id = u.account.id " +
            "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
            "WHERE (u.account.role.id = 3) " +
            "AND (u.account.status.id = 1) " +
            "AND (b.isActive = true) " +
            "AND (b.status.id = 6) " +
            "AND (u.account.isActive = true) " +
            "AND (rev.isActive = true OR rev IS NULL) " +
            "AND (u.gender = 'nam')" +
            "GROUP BY u.id " +
            "ORDER BY COUNT(DISTINCT b.id) DESC")
    List<AccountCCDVDTO> findTop8FemaleAccountCCDV();

    @Query("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
            "from UserProfile u, in (u.supplies) sup " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup in (:list)) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
            "group by u.id ")
    List<UserDTO> getBySupplies(List<Supply> list);


}
