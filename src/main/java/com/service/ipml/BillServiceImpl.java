package com.service.ipml;

import com.model.Account;
import com.model.Bill;
import com.model.Status;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    IStatusRepository iStatusRepository;

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

    @Override
    public Optional<List<Bill>> findAllByAccountCCDV_IOrderByIdDesc(long id) {
        Sort descendingSortById = Sort.by(Sort.Direction.DESC, "id");
        return iBillRepository.getAllByAccountCCDV_Id(id,descendingSortById);
    }
    @Override
    public String confirmBill(long id) {
        try {
            Bill bill = getById(id);
            Status status = iStatusRepository.findById(5L).get();
            bill.setStatus(status);
            edit(bill);
            return "Xác nhận thành công";
        } catch (Exception e) {
            return "Đơn đã được xác nhận rồi";
        }
    }

    @Override
    public Optional<List<Bill>> getAllByAccountCCDV_IdOrAccountUser_Id(long id) {
        Sort descendingSortById = Sort.by(Sort.Direction.DESC, "id");
        return iBillRepository.getBillByAccountCCDV_IdOrAccountUser_Id(id,descendingSortById);
    }
}
