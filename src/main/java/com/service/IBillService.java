package com.service;

import com.model.Bill;

import java.util.List;
import java.util.Optional;

public interface IBillService extends ICrudService<Bill>{
    List<Bill> getAllByAccountCCDV_Id(long id);
    Optional<List<Bill>> findAllByAccountCCDV_IOrderByIdDesc(long id);
    String confirmBill(long id);
    Optional<List<Bill>> getAllByAccountCCDV_IdOrAccountUser_Id(long id);
}
