package com.repository;

import com.model.Account;
import com.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
    List<Bill> getAllByAccountCCDV_Id(long id);
//    @Query(nativeQuery = true, value = "select * from bill  where accountccdv_id= :accountccdv_id  ")
//    List<Bill> getAllBillByAccountCCDV(@Param("accountccdv_id") long accountccdv_id);


//    @Query( "SELECT b.accountUser.id, MAX(b.id) AS bill_id\n" +
//            "FROM Bill b JOIN Account a ON b.accountUser.id = a.id\n" +
//            "WHERE b.accountUser.id = 15\n" +
//            "GROUP BY b.accountUser.id\n" +
//            "LIMIT 3\n")
//    Optional<?> getAccount_User_IdAndBill_IdByAccount_CCDV_Id(@Param("accountUser.id") long account_user_id);
}
