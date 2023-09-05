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
    @Query( "SELECT new com.model.dto.AccountCCDVDTO( u,a,'', avg(rev.rating), count(rev.rating), COUNT(b.id)) " +
            "FROM UserProfile u  left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "left outer join Account a on a.id=u.account.id left outer join Bill b on b.accountCCDV.id = a.id" +
            " where (u.account.role.id = 3) and (u.account.status.id = 1)  and (b.isActive = true) and (b.status.id = 6)" +
            " and (u.account.isActive = true ) and (rev.isActive = true or rev is null) and (u.gender ='nam' or u.gender ='nu') " +
            "group by u.id order by COUNT(b.id) desc")
    List<AccountCCDVDTO> findTop4MaleAn8FemaleAccountCCDV();

    @Query("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
            "from UserProfile u, in (u.supplies) sup " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup in (:list)) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
            "group by u.id ")
    List<UserDTO> getBySupplies(List<Supply> list);


}
