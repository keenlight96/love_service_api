package com.repository;

import com.model.Bill;
import com.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
    List<Bill> getAllByAccountCCDV_Id(long id);
//    @Query(nativeQuery = true, value = "select * from bill  where accountccdv_id= :accountccdv_id  ")
//    List<Bill> getAllBillByAccountCCDV(@Param("accountccdv_id") long accountccdv_id);
}
