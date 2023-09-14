package com.repository;

import com.model.Account;
import com.model.Bill;
import com.model.UserProfile;
import com.model.dto.AccountCCDVDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getAllByAccountCCDV_Id(long id);
//    @Query(nativeQuery = true, value = "select * from bill  where accountccdv_id= :accountccdv_id  ")
//    List<Bill> getAllBillByAccountCCDV(@Param("accountccdv_id") long accountccdv_id);

    @Query(nativeQuery = true, value = "select * from bill  where (status_id= 5 or status_id= 4 )and accountCCDV_id=:id and ( date_start   between  :#{#dayNow} and DATE_ADD(:#{#dayNow}, INTERVAL 7 DAY) )")
    Optional<List<Bill>> getAllBill7DayByID(@Param("id") long id,@Param("dayNow")String dayNow);

    Optional<List<Bill>> getAllByAccountCCDV_Id(long accountId, Sort sort);
    Optional<List<Bill>> getBillByAccountUser_Id(long accountId, Sort sort);
}

