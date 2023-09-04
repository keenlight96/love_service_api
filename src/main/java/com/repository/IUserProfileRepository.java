package com.repository;

import com.model.Account;
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
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    UserProfile getByAccount_Id(Long id);

//    @Query(value = "select new com.model.dto.UserDTO(u) from UserProfile u " +
//            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (u.isActive = true) and (u.account.isActive = true) " +
//            "order by u.id desc")
//    List<UserDTO> getNewestCCDVsTest();


}
