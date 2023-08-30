package com.service.ipml;

import com.model.Comment;
import com.model.Status;
import com.repository.IStatusRepository;
import com.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements IStatusService {
    @Autowired
    IStatusRepository iStatusRepository;

    @Override
    public List<Status> getAll() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status getById(long id) {
        Optional<Status> status = iStatusRepository.findById(id);
        if (status.isPresent()) {
            return status.get();
        } else {
            return null;
        }
    }

    @Override
    public Status create(Status status) {
        return iStatusRepository.save(status);
    }

    @Override
    public Status edit(Status status) {
        return iStatusRepository.save(status);
    }

    @Override
    public void deleteById(long id) {
        iStatusRepository.deleteById(id);
    }
}
