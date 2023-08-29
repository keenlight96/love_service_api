package com.repository;

import com.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInterestRepository extends JpaRepository<Interest,Long> {
}
