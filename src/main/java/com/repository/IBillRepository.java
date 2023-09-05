package com.repository;

import com.model.Bill;
import com.model.dto.AccountCCDVDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
}
