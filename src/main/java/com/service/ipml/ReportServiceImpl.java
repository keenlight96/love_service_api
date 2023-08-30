package com.service.ipml;

import com.model.Comment;
import com.model.Report;
import com.repository.IReportRepository;
import com.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    IReportRepository iReportRepository;
    @Override
    public List<Report> getAll() {
        return iReportRepository.findAll();
    }

    @Override
    public Report getById(long id) {
        Optional<Report> report = iReportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        } else {
            return null;
        }
    }

    @Override
    public Report create(Report report) {
        return iReportRepository.save(report);
    }

    @Override
    public Report edit(Report report) {
        return iReportRepository.save(report);
    }

    @Override
    public void deleteById(long id) {
        iReportRepository.deleteById(id);
    }
}
