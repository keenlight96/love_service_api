package com.service;

import com.model.Account;
import com.model.Bill;
import com.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.model.dto.BillMessageDTO;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface IBillService extends ICrudService<Bill>{
    List<Bill> getAllByAccountCCDV_Id(long id);
    BillMessageDTO createBill(Bill bill);
    List<Bill> getBills7DayByAccountCCDV_Id(long id);
    Optional<List<Bill>> findAllByAccountCCDV_IOrderByIdDesc(long id);
    String confirmBill(long id);
    Optional<List<Bill>> getBillByAccountUser_IdDesc(long id);
    String completeBill(long idBill);
    String cancelBill(long idBill, Account cancelerAccount,String message);
    Bill getLatestBillBy2Acc(Long ccdvId, Long userId);
    List<Bill> getAllBills();
   List<Bill> getAllBillByAccountUser(long id);
//   Bill getBillAccountUserById(long idAccountUser, long idBill);
    List<Bill> findBillByStatus(Long idStatus);

}
