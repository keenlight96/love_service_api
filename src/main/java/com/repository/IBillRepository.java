package com.repository;

import com.model.Account;
import com.model.Bill;
import com.model.UserProfile;
import com.model.dto.AccountCCDVDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getAllByAccountCCDV_Id(long id);
//    @Query(nativeQuery = true, value = "select * from bill  where accountccdv_id= :accountccdv_id  ")
//    List<Bill> getAllBillByAccountCCDV(@Param("accountccdv_id") long accountccdv_id);
    Optional<List<Bill>> getAllByAccountCCDV_Id(long accountId, Sort sort);
    Optional<List<Bill>> getBillByAccountUser_Id(long accountId, Sort sort);

    //lay ra 1 list bill cua nguoi dung
    @Query(nativeQuery = true, value = "select * from bill  where account_user_id= :account_user_id  ")
    List<Bill> getAllBillByAccountUser(@Param("account_user_id") long account_user_id);
//    @Query(nativeQuery = true, value = "select * from bill  where account_user_id= :account_user_id and id= :id ")
//    Bill getBillDetailByAccountUser(@Param("account_user_id") long account_user_id, @Param("id") long id);




}
