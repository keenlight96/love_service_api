package com.repository;

import com.model.Bill;
import com.model.dto.AccountDTOCCDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
    @Query("SELECT  AccountDTOCCDV(u.lastName, u.account.avatar, u.basicRequest, u.supply, u.price, COUNT(b), rs) " +
            "FROM UserProfile u JOIN Account a on u.account.id = a.id JOIN Role r ON a.role.id = r.id JOIN Status s on " +
            "a.status.id = s.id JOIN Review rs on  a.id = rs.accountCCDV.id JOIN Bill b on a.id = b.accountCCDV.id  " +
            "and b.status.id = s.id " +
            "WHERE u.gender = 'Nam' and r.nameRole ='ROLE_CCDV' and s.nameStatus = 'complete' " +
            "GROUP BY b.accountCCDV.id ORDER BY COUNT(b) DESC Limit 4")
    List<AccountDTOCCDV> findTop4MaleAccountCCDV();

    @Query("SELECT AccountDTOCCDV(u.lastName, u.account.avatar, u.basicRequest, u.supply, u.price, COUNT(b), u.reviews) " +
            "FROM UserProfile u JOIN Bill b WHERE u.gender = 'Nữ' GROUP BY u ORDER BY COUNT(b) DESC")
    List<AccountDTOCCDV> findTop8FemaleAccountCCDV();


//    @Query("SELECT NEW com.example.AccountDTOCCDV(u.lastName, u.account.avatar, u.basicRequest, u.supply, u.price, COUNT(b), u.account.reviews) " +
//            "FROM UserProfile u JOIN u.account.reviews rs JOIN u.account b " +
//            "WHERE u.gender = 'Nam' AND u.account.role.nameRole = 'ROLE_CCDV' AND u.account.status.nameStatus = 'complete' " +
//            "GROUP BY u.account.id " +
//            "ORDER BY COUNT(b) DESC " +
//            "LIMIT 4")
//    List<AccountDTOCCDV> findTop4MaleAccountCCDV();

}
