package com.service.ipml;

import com.model.Bill;
import com.repository.IBillRepository;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillRepository iBillRepository;

    @Override
    public List<Bill> getAll() {
        return null;
    }

    @Override
    public Bill getById(long id) {
        return null;
    }

    @Override
    public Bill create(Bill bill) {
        return null;
    }

    @Override
    public Bill edit(Bill bill) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
