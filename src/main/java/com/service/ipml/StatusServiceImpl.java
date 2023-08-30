package com.service.ipml;

import com.model.Status;
import com.repository.IStatusRepository;
import com.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements IStatusService {
    @Autowired
    IStatusRepository iStatusRepository;

    @Override
    public List<Status> getAll() {
        return null;
    }

    @Override
    public Status getById(long id) {
        return null;
    }

    @Override
    public Status create(Status status) {
        return null;
    }

    @Override
    public Status edit(Status status) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
