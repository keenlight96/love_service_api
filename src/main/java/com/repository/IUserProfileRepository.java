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
            "Status s on a.status.id = s.id JOIN Bill b on a.id = b.accountCCDV.id WHERE u.gender = 'nam' AND r.nameRole = 'ROLE_CCDV' ")
    List<UserProfile> findTopMaleRenters();
    @Query("SELECT b.accountCCDV, COUNT(b) FROM Bill b GROUP BY b.accountCCDV")
    List<Object[]> countBillsByAccountCCDV();
    UserProfile getByAccount_Id(Long id);
}
