package com.service;
import com.model.Bill;
import com.model.Revenue;
import com.model.dto.TotalRevenueDTO;

import java.util.Date;
import java.util.List;

public interface IRevenueService extends ICrudService<Revenue>{
    List<TotalRevenueDTO> getTotalRevenueByDayForAccount(long idAccountCCDV, Date startOfMonth, Date endOfMonth);


}
