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

}
