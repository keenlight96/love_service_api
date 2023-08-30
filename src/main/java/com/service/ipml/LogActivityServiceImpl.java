package com.service.ipml;

import com.model.LogActivity;
import com.service.ILogActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogActivityServiceImpl implements ILogActivityService {
    @Autowired
    ILogActivityService iLogActivityService;
    @Override
    public List<LogActivity> getAll() {
        return null;
    }

    @Override
    public LogActivity getById(long id) {
        return null;
    }

    @Override
    public LogActivity create(LogActivity logActivity) {
        return null;
    }

    @Override
    public LogActivity edit(LogActivity logActivity) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
