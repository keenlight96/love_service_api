package com.service.ipml;

import com.model.Comment;
import com.model.Report;
import com.model.Revenue;
import com.repository.IRevenueRepository;
import com.service.IReportService;
import com.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
