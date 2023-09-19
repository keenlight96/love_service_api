package com.repository;
import com.model.Bill;
import com.model.Revenue;
import com.model.dto.TotalRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface IRevenueRepository extends JpaRepository<Revenue, Long> {
    @Query(nativeQuery = true,value = "SELECT DISTINCT b.date_end FROM Bill b "+
            "WHERE b.accountccdv_id = :idAccountCCDV " +
            "AND b.status_id = 6 "+
            "AND b.is_active= true " +
            "AND b.date_end >= :startOfMonth "+
            "AND b.date_end <= :endOfMonth "+
            "ORDER BY b.date_end asc ")
    List<Date> getDayDistinct (long idAccountCCDV, @Param("startOfMonth") Date startOfMonth, @Param("endOfMonth") Date endOfMonth);

    @Query(nativeQuery = true, value = "SELECT SUM(b.total) FROM Bill b "+
            "WHERE b.accountccdv_id = :idAccountCCDV " +
            "AND b.status_id = 6 "+
            "AND b.is_active= true " +
            "AND b.date_end = :dateEnd ")
    Long getRevenueByDate(@Param("idAccountCCDV") long idAccountCCDV, @Param("dateEnd") Date dateEnd);
}
