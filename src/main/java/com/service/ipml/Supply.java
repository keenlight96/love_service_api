package com.service.ipml;

import com.repository.ISupplyRepository;
import com.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Supply implements ISupplyService {
    @Autowired
    ISupplyRepository iSupplyRepository;

    @Override
    public List<com.model.Supply> getAll() {
        return null;
    }

    @Override
    public com.model.Supply getById(int id) {
        return null;
    }

    @Override
    public com.model.Supply create(com.model.Supply supply) {
        return null;
    }

    @Override
    public com.model.Supply edit(com.model.Supply supply) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
