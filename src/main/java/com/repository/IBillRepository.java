package com.repository;

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
    Optional<List<Bill>> getAllByAccountCCDV_Id(long accountId, Sort sort);
    @Query("SELECT b FROM Bill b WHERE b.accountCCDV.id = :accountId OR b.accountUser.id = :accountId")
    Optional<List<Bill>> getBillByAccountCCDV_IdOrAccountUser_Id(@Param("accountId") long accountId, Sort sort);
}
