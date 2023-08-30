package com.service.ipml;

import com.model.Report;
import com.repository.IReportRepository;
import com.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    IReportRepository iReportRepository;
    @Override
    public List<Report> getAll() {
        return null;
    }

    @Override
    public Report getById(long id) {
        return null;
    }

    @Override
    public Report create(Report report) {
        return null;
    }

    @Override
    public Report edit(Report report) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
