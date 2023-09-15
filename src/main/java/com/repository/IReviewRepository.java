package com.repository;

import com.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review,Long> {
    List<Review> getAllByAccountCCDV_UsernameAndIsActiveIsTrue(String username);

    @Query("select r from Review r " +
            "where r.accountCCDV.username = :username " +
            "and r.isActive = true " +
            "order by r.id desc")
    List<Review> getAllActiveByAccountCCDV_Username_Desc(String username);
}
