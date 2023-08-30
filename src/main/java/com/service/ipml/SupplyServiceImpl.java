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
        return null;
    }


    public Supply getById(long id) {
        return iSupplyRepository.findById(id).get();
    }

    @Override
    public Supply create(Supply supply) {
        return iSupplyRepository.save(supply);
    }

    @Override
    public Supply edit(Supply supply) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
