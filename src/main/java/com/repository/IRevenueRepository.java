package com.repository;
import com.model.Revenue;
import com.model.dto.TotalRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface IRevenueRepository extends JpaRepository<Revenue, Long> {
//    lấy ngày hiên tại hôm nay
//    @Query(nativeQuery = true,value = "SELECT SUM(b.total) FROM Bill b " +
//            "WHERE (b.accountccdv_id = :idAccountCCDV) " +
//            "AND (date_start   between  :dayNow " +
//            "AND DATE_ADD(:dayNow, INTERVAL 1 DAY)) ")
//    Long getTotalRevenueByDayAccount (@Param("idAccountCCDV") long idAccountCCDV, @Param("dayNow") String dayNow );
//    @Query(nativeQuery = true,value = "SELECT NEW com.model.dto.TotalRevenueDTO(b.dateEnd, SUM(b.total)  ) FROM Bill b " +
//            "WHERE b.accountccdv_id = :idAccountCCDV AND b.date_start >= :startOfMonth " +
//            "AND b.status.id = 6 " +
//            "AND b.isActive= true " +
//            "AND (:startOfMonth is not null OR b.dateStart = :startOfMonth) = 6 " +
//            "AND b.isActive= true " +
//            "AND b.dateStart < DATE_ADD(:endOfMonth, INTERVAL 1 DAY) " +
//            "GROUP BY date_start ")
//    List<TotalRevenueDTO> getTotalRevenueByDayForAccount(@Param("idAccountCCDV") long idAccountCCDV,
//                                                         @Param("startOfMonth") Date startOfMonth,
//                                                         @Param("endOfMonth") Date endOfMonth);
}
