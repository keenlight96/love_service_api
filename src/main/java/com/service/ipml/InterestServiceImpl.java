package com.service.ipml;

import com.model.Interest;
import com.repository.IInterestRepository;
import com.service.IInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl implements IInterestService {
    @Autowired
    IInterestRepository iInterestRepository;
    @Override
    public List<Interest> getAll() {
        return null;
    }

    @Override
    public Interest getById(long id) {
        return null;
    }

    @Override
    public Interest create(Interest interest) {
        return null;
    }

    @Override
    public Interest edit(Interest interest) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
