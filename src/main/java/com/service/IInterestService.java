package com.service;

import com.model.Bill;
import com.model.Interest;

import java.util.List;

public interface IInterestService extends ICrudService<Interest>{
    List<Interest> getAllInterestByAccountCCDV_Id(long id);
}
