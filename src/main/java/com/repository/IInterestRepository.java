package com.repository;

import com.model.Image;
import com.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInterestRepository extends JpaRepository<Interest,Long> {
    List<Interest> getInterestsById(long id);
}
