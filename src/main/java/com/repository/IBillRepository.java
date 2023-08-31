package com.repository;

import com.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
    List<Bill> getAllByAccountCCDV_Id(long id);
}
