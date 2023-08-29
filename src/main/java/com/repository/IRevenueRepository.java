package com.repository;

import com.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRevenueRepository extends JpaRepository<Revenue,Long> {
}
