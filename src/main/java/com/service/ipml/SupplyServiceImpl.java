package com.service.ipml;

import com.model.Supply;
import com.repository.ISupplyRepository;
import com.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements ISupplyService {
    @Autowired
    ISupplyRepository iSupplyRepository;

    @Override
    public List<Supply> getAll() {
        return iSupplyRepository.findAll();
    }

    @Override
    public Supply getById(long id) {
        return iSupplyRepository.findById(id).get();
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
