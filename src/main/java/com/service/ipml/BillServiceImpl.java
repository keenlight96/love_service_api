package com.service.ipml;

import com.model.Account;
import com.model.Bill;
import com.model.UserProfile;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.repository.IUserProfileRepository;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    IStatusRepository iStatusRepository;
    @Autowired
    IUserProfileRepository iUserProfileRepository;

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
    public boolean createBill(Bill bill) {
        UserProfile userProfile = iUserProfileRepository.getById(bill.getAccountUser().getId());
        if (userProfile.getBalance() > bill.getTotal()) {
            userProfile.setBalance(iUserProfileRepository.getById(bill.getAccountUser().getId()).getBalance() - bill.getTotal());
            bill.setStatus(iStatusRepository.findById(4L).get());
            bill.setIsActive(true);
            iUserProfileRepository.save(userProfile);
            iBillRepository.save(bill);
            return true;
        }
        return false;
    }

    @Override
    public List<Bill> getBills7DayByAccountCCDV_Id(long id) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(now)+" 00:00:00";
        return iBillRepository.getAllBill7DayByID(id,formattedDate).get();
    }
}
