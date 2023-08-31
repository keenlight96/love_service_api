package com.service.ipml;

import com.model.Account;
import com.model.Bill;
import com.repository.IBillRepository;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillRepository iBillRepository;

    @Override
    public List<Bill> getAll() {
        return iBillRepository.findAll();
    }

    @Override
    public Bill getById(long id) {
        Optional<Bill> bill = iBillRepository.findById(id);
        if (bill.isPresent()) {
            return bill.get();
        } else {
            return null;
        }
    }


    @Override
    public Bill create(Bill bill) {
        return iBillRepository.save(bill);
    }

    @Override
    public Bill edit(Bill bill) {
        return iBillRepository.save(bill);
    }

    @Override
    public void deleteById(long id) {
        iBillRepository.deleteById(id);
    }

    @Override
    public List<Bill> getAllByAccountCCDV_Id(long id) {
        return iBillRepository.getAllByAccountCCDV_Id(id);
    }
}
