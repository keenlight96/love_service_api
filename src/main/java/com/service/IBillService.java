package com.service;

import com.model.Bill;

import java.util.List;

public interface IBillService extends ICrudService<Bill>{
    List<Bill> getAllByAccountCCDV_Id(long id);
    boolean createBill(Bill bill);
    List<Bill> getBills7DayByAccountCCDV_Id(long id);
}
