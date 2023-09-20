package com.service.ipml;

import com.model.Bill;
import com.model.Revenue;
import com.model.dto.TotalRevenueDTO;
import com.repository.IRevenueRepository;
import com.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RevenueServiceImpl implements IRevenueService {
    @Autowired
    IRevenueRepository iRevenueRepository;

    @Override
    public List<Revenue> getAll() {
        return iRevenueRepository.findAll();
    }

    @Override
    public Revenue getById(long id) {
        Optional<Revenue> revenue = iRevenueRepository.findById(id);
        if (revenue.isPresent()) {
            return revenue.get();
        } else {
            return null;
        }
    }

    @Override
    public Revenue create(Revenue revenue) {
        return iRevenueRepository.save(revenue);
    }

    @Override
    public Revenue edit(Revenue revenue) {
        return iRevenueRepository.save(revenue);
    }

    @Override
    public void deleteById(long id) {
        iRevenueRepository.deleteById(id);
    }

    @Override
    public List<TotalRevenueDTO> getTotalRevenueByDayForAccount(long idAccountCCDV, Date startOfMonth, Date endOfMonth) {
        List<Date> dateList = iRevenueRepository.getDayDistinct(idAccountCCDV,startOfMonth,endOfMonth);
        List<TotalRevenueDTO> totalRevenueDTOList = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            long revenue = iRevenueRepository.getRevenueByDate(idAccountCCDV,dateList.get(i));
            totalRevenueDTOList.add(new TotalRevenueDTO(revenue,dateList.get(i)));
        }
        return totalRevenueDTOList;
    }
//    @Override
//    public List<TotalRevenueDTO> getTotalRevenueByDayForAccount(long idAccountCCDV, Date startOfMonth, Date endOfMonth) {
//        return iRevenueRepository.getTotalRevenueByDayForAccount(idAccountCCDV, startOfMonth, endOfMonth);
//    }

//    @Override
//    public Long getTotalRevenueByDayAccount(long idAccountCCDV) {
//        Date dayNow = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String str = simpleDateFormat.format(dayNow) + " 00.00.00";
//        long a= iRevenueRepository.getTotalRevenueByDayAccount(idAccountCCDV, str);
//        return a;
//    }

//    @Override
//    public Long getTotalRevenueByDayForAccount(long idAccountCCDV,String startOfMonth, String endOfMonth) {
//        Date dayNow = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String str = simpleDateFormat.format(dayNow) + " 00.00.00";
//        long a= iRevenueRepository.getTotalRevenueByDayForAccount(idAccountCCDV, str,);
//        return a;
//    }

}
