package com.service.ipml;

import com.model.Report;
import com.model.Revenue;
import com.repository.IRevenueRepository;
import com.service.IReportService;
import com.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements IRevenueService {
    @Autowired
    IRevenueRepository iRevenueRepository;

    @Override
    public List<Revenue> getAll() {
        return null;
    }

    @Override
    public Revenue getById(long id) {
        return null;
    }

    @Override
    public Revenue create(Revenue revenue) {
        return null;
    }

    @Override
    public Revenue edit(Revenue revenue) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
