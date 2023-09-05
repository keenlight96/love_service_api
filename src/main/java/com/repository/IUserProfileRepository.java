package com.repository;

import com.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
    UserProfile getByAccount_Id(Long id);
    @Query(nativeQuery = true, value = "select * from user_profile  order by views desc  ")
    List<UserProfile> getTop6HotServiceProviders();
    //filter user_profile
    @Query(nativeQuery = true, value = "select * from user_profile where (:first_name is null or first_name like %:first_name%) " +
            "and (:last_name is null or last_name like %:last_name%) " +
            "and (:birthday is null or year(birthday)= :birthday) " +
            "and (:gender is null or gender= :gender) " +
            "and (:address is null or address= :address) " +
            "and (:views is null or views= :views) ")
    List<UserProfile> getAllUserProfileByFilter(@Param("first_name") String first_name,
                                                @Param("last_name") String last_name,
                                                @Param("birthday") int birthday,
                                                @Param("gender") String gender,
                                                @Param("address") String address,
                                                @Param("views") long views);

}
