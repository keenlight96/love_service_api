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
//    @Query("SELECT u FROM UserProfile u WHERE u.gender = 'Nam' ORDER BY u.price DESC")
//    List<UserProfile> findTopMaleRenters();
//
//    @Query("SELECT u FROM UserProfile u WHERE u.gender = 'Nữ' ORDER BY u.price DESC")
//    List<UserProfile> findTopFemaleRenters();
//    @Query("SELECT u FROM UserProfile u JOIN FETCH u.account a JOIN FETCH u. b GROUP BY u.id ORDER BY COUNT(b) DESC")
//    List<UserProfile> findTopRentersByBillCount();
}
