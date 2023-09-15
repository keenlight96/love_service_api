package com.service.ipml;

import com.model.*;
import com.repository.IAccountRepository;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.repository.IUserProfileRepository;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements IBillService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    IStatusRepository iStatusRepository;
    @Autowired
    IAccountRepository iAccountRepository;
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
    public Optional<List<Bill>> findAllByAccountCCDV_IOrderByIdDesc(long id) {
        Sort descendingSortById = Sort.by(Sort.Direction.DESC, "id");
        return iBillRepository.getAllByAccountCCDV_Id(id,descendingSortById);
    }
    @Override
    public String confirmBill(long id) {
        try {
            Bill bill = getById(id);
            Status status = iStatusRepository.findById(5L).get(); // đã nhận
            bill.setStatus(status);
            edit(bill);
            return "Xác nhận thành công";
        } catch (Exception e) {
            return "Đơn đã được xác nhận rồi";
        }
    }

    @Override
    public Optional<List<Bill>> getBillByAccountUser_IdDesc(long id) {
        Sort descendingSortById = Sort.by(Sort.Direction.DESC, "id");
        return iBillRepository.getBillByAccountUser_Id(id,descendingSortById);
    }

    // user xác nhận và tự động cộng tiền cho người ccdv
    @Override
    public String completeBill(long idBill) {
        try {
            Bill bill = iBillRepository.findById(idBill).get();
            Status status = iStatusRepository.findById(6L).get(); // tìm ra trạng thái complete
            UserProfile userProfileCCDV = iUserProfileRepository.findUserProfileByAccount_Id(bill.getAccountCCDV().getId()).get();
            if (bill.getStatus().getNameStatus().equals("recevied")){
                userProfileCCDV.setBalance(userProfileCCDV.getBalance() + bill.getTotal());
                bill.setStatus(status);
                edit(bill);
                iUserProfileRepository.save(userProfileCCDV);
                return "Xác nhận thành công";
            }else {
                return "Đơn đã được xác nhận";
            }
        }catch (Exception e){
            return "không tìm thấy hóa đơn";
        }
    }

    @Override
    public String cancelBill(long idBill, Account cancelerAccount, String message) {   // thiếu message đi cùng
        try {
            Bill bill = iBillRepository.findById(idBill).get();
            UserProfile userProfile = iUserProfileRepository.getByAccount_Id(cancelerAccount.getId());
            if (bill.getStatus().getNameStatus().equals("wait")){
                if (cancelerAccount.getRole().getNameRole().equals("ROLE_USER")){
                    Status status = iStatusRepository.findById(7L).get(); // trạng thái 7 cancel from wait by user
                    bill.setStatus(status);
                    bill.setUserMessage(message);
                     edit(bill);
                     return "Bạn đã hủy thành công đơn hàng";
                }else if (cancelerAccount.getRole().getNameRole().equals("ROLE_CCDV")){
                    Status status = iStatusRepository.findById(8L).get(); // trạng thái 8 cancel from wait by ccdv
                    bill.setStatus(status);
                    bill.setCcdvMessage(message);
                    edit(bill);
                    return "Bạn đã hủy thành công";
                }
            }else if (bill.getStatus().getNameStatus().equals("recevied")){
                if (cancelerAccount.getRole().getNameRole().equals("ROLE_USER")){
                    Status status = iStatusRepository.findById(9L).get(); // trạng thái 9 cancel from recevied by user
                    bill.setStatus(status);
                    bill.setUserMessage(message);
                    userProfile.setBalance((long) (userProfile.getBalance()+(bill.getTotal()*0.75)));
                    edit(bill);
                    return "Bạn đã hủy thành công đơn hàng ";
                }else if (cancelerAccount.getRole().getNameRole().equals("ROLE_CCDV")){
                    Status status = iStatusRepository.findById(10L).get(); // trạng thái 10 cancel from recevied by ccdv
                    bill.setStatus(status);
                    bill.setCcdvMessage(message);
                    edit(bill);
                    return "Bạn đã hủy thành công";
                }
            }
        }catch (Exception e){
            return "Không tìm thấy hóa đơn";
        }
        return "Không tìm thấy hóa đơn";
    }

    @Override
    public Bill getLatestBillBy2Acc(Long ccdvId, Long userId) {
        List<Bill> results = entityManager.createQuery("select b from Bill b " +
                        "where b.accountCCDV.id = :ccdvId and b.accountUser.id = :userId " +
                        "and b.isActive = true and b.status.id = 6 " +
                        "order by b.id desc")
                .setMaxResults(1)
                .setParameter("ccdvId", ccdvId)
                .setParameter("userId", userId)
                .getResultList();
        return results.get(0);
    }
}
