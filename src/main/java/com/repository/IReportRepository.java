package com.repository;

import com.model.Account;
import com.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReportRepository extends JpaRepository<Report, Long> {

    Optional<List<Report>> getAllByBill_IdAndSend_Id(long idBill,long idAcc);
}
