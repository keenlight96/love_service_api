package com.service;

import com.model.Bill;

import java.util.List;

public interface IBillService extends ICrudService<Bill>{
    List<Bill> getAllByAccountCCDV_Id(long id);
   List<Bill> getAllBillByAccountUser(long id);
//   Bill getBillAccountUserById(long idAccountUser, long idBill);
}
