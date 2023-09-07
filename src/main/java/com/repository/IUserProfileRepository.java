package com.repository;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile getByAccount_Id(Long id);

//    @Query(nativeQuery = true, value = "(SELECT up.* FROM UserProfile up" +
//            "left join Account a on up.account_id=a.id" +
//            "where a.role_id=3 and  a.is_active=1" +
//            "and up.is_active=1 and up.gender like :gender" +
//            "order by up.date_create desc limit 6) union all " +
//            "(SELECT up.* FROM UserProfile up" +
//            "join Account a on up.account_id=a.id" +
//            "where a.role_id=3 and  a.is_active=1" +
//            "and up.is_active=1 and up.gender like :gender" +
//            "order by up.date_create asc limit 6)")
//    Optional<List<UserProfile>> getUserHaveSameGender(@Param("gender") String gender);

    @Query("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
            "from UserProfile u, in (u.supplies) sup " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup in (:list)) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
            "group by u.id ")
    List<UserDTO> getBySupplies(List<Supply> list);


}
