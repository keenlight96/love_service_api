package com.service.ipml;

import com.model.Comment;
import com.model.LogActivity;
import com.repository.ILogActivityRepository;
import com.service.ILogActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogActivityServiceImpl implements ILogActivityService {
    @Autowired
    ILogActivityRepository iLogActivityRepository;
    @Override
    public List<LogActivity> getAll() {
        return iLogActivityRepository.findAll();
    }

    @Override
    public LogActivity getById(long id) {
        Optional<LogActivity> logActivity = iLogActivityRepository.findById(id);
        if (logActivity.isPresent()) {
            return logActivity.get();
        } else {
            return null;
        }
    }

    @Override
    public LogActivity create(LogActivity logActivity) {
        return iLogActivityRepository.save(logActivity);
    }

    @Override
    public LogActivity edit(LogActivity logActivity) {
        return iLogActivityRepository.save(logActivity);
    }

    @Override
    public void deleteById(long id) {
        iLogActivityRepository.deleteById(id);
    }
}
