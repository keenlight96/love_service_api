package com.service.ipml;

import com.model.Comment;
import com.model.Supply;
import com.repository.ISupplyRepository;
import com.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Supply> supply = iSupplyRepository.findById(id);
        if (supply.isPresent()) {
            return supply.get();
        } else {
            return null;
        }
    }

    @Override
    public Supply create(Supply supply) {
        return iSupplyRepository.save(supply);
    }

    @Override
    public Supply edit(Supply supply) {
        return iSupplyRepository.save(supply);
    }

    @Override
    public void deleteById(long id) {
        iSupplyRepository.deleteById(id);
    }
}
