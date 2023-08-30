package com.repository;

import com.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
    @Query("SELECT u FROM UserProfile u JOIN Account a on u.account.id = a.id JOIN Role r on a.role.id = r.id JOIN " +
            "Status s on a.status.id = s.id JOIN Bill b on a.id = b.accountCCDV WHERE u.gender = 'nam' ORDER BY  ")
    List<UserProfile> findTopMaleRenters();
    @Query("SELECT b.accountCCDV, COUNT(b) FROM Bill b GROUP BY b.accountCCDV")
    List<Object[]> countBillsByAccountCCDV();

//    @Query("SELECT u FROM UserProfile u WHERE u.gender = 'Nữ' ORDER BY u.numberOfRentals DESC")
//    List<UserProfile> findTopFemaleRenters();
}
