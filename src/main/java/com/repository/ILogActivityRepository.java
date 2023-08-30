package com.repository;

import com.model.LogActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogActivityRepository extends JpaRepository<LogActivity,Long> {
}
