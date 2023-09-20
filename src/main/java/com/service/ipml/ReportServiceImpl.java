package com.service.ipml;

import com.model.Account;
import com.model.Comment;
import com.model.Report;
import com.repository.IReportRepository;
import com.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    IReportRepository iReportRepository;
    @PersistenceContext
    EntityManager entityManager;

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

    @Override
    public List<Report> getAccountReceiverReport(String usernameParam) {
        String username = "%" + usernameParam + "%";
        if (username.equals("")) {
            username = null;
        }

        String queryString = "SELECT rp FROM Report rp JOIN Account a ON rp.receiver.id = a.id " +
                "WHERE (a.isActive = true) " +
                "AND (:usernameParam IS NULL OR rp.receiver.username LIKE :usernameParam) " +
                "GROUP BY rp.id " +
                "ORDER BY rp.id DESC";

        TypedQuery<Report> query = entityManager.createQuery(queryString, Report.class);
        query.setParameter("usernameParam", username);

        List<Report> reportList = query.getResultList();
        return reportList;
    }

    @Override
    public String sendReport(Report report) {
       List<Report> list = iReportRepository.getAllByBill_IdAndSend_Id(report.getBill().getId(),report.getSend().getId()).get();
        if (list.size() == 0) {
            report.setIsActive(true);
            report.setStatus(null);
            iReportRepository.save(report);
            return "Tố cáo thành công";
        }
        return "Bạn đã tố cáo rồi";
    }
}
