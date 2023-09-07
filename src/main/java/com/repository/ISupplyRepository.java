package com.repository;

import com.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplyRepository extends JpaRepository<Supply,Long> {
    @Query("select s from Supply s where s.isActive = true")
    List<Supply> findAllActive();
}
