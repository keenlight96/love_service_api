package com.repository;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
    UserProfile getByAccount_Id(Long id);
    @Query(nativeQuery = true, value = "select * from user_profile  order by views desc limit 8 ")
    List<UserProfile> getTop6HotServiceProviders();
    @Query("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
            "from UserProfile u, in (u.supplies) sup " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup in (:list)) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev = '') " +
            "group by u.id ")
    List<UserDTO> getBySupplies(List<Supply> list);

}
