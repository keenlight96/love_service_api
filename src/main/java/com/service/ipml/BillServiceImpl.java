package com.service.ipml;

import com.model.*;
import com.model.Account;
import com.model.Bill;
import com.model.UserProfile;
import com.model.Status;
import com.model.dto.BillMessageDTO;
import com.repository.IAccountRepository;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.repository.IUserProfileRepository;
import com.service.IAccountService;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.Date;
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
    IAccountService iAccountService;
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
    public BillMessageDTO createBill(Bill bill) {
        UserProfile userProfile = iUserProfileRepository.getByAccount_Id(bill.getAccountUser().getId());
        Account accountCCDV = iAccountService.getById(bill.getAccountCCDV().getId());
        System.out.println("---------------------------");
        System.out.println(bill.getAccountUser().getId());
        System.out.println(userProfile.getBalance());
        System.out.println(bill.getTotal());
        if (userProfile.getBalance() > bill.getTotal()) {
            if (accountCCDV.getStatus().getId() == 1) {
                userProfile.setBalance(userProfile.getBalance() - bill.getTotal());
                bill.setStatus(iStatusRepository.findById(4L).get());
                bill.setIsActive(true);
                iUserProfileRepository.save(userProfile);
                iBillRepository.save(bill);
                return new BillMessageDTO(bill,"Tạo đơn thuê thành công");
            }
            return new BillMessageDTO(null," Người CCDV hiện không khả dụng ");
        } return new BillMessageDTO(null," Số dư của bạn không đủ ");
    }

    @Override
    public List<Bill> getBills7DayByAccountCCDV_Id(long id) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(now) + " 00:00:00";
        return iBillRepository.getAllBill7DayByID(id, formattedDate).get();
    }

    @Override
    public Optional<List<Bill>> findAllByAccountCCDV_IOrderByIdDesc(long id) {
        Sort descendingSortById = Sort.by(Sort.Direction.DESC, "id");
        return iBillRepository.getAllByAccountCCDV_Id(id, descendingSortById);
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
        return iBillRepository.getBillByAccountUser_Id(id, descendingSortById);
    }

    // user xác nhận và tự động cộng tiền cho người ccdv
    @Override
    public String completeBill(long idBill) {
        try {
            Bill bill = iBillRepository.findById(idBill).get();
            Status status = iStatusRepository.findById(6L).get(); // tìm ra trạng thái complete
            UserProfile userProfileCCDV = iUserProfileRepository.findUserProfileByAccount_Id(bill.getAccountCCDV().getId()).get();
            if (bill.getStatus().getNameStatus().equals("recevied")) {
                userProfileCCDV.setBalance(userProfileCCDV.getBalance() + bill.getTotal());
                bill.setStatus(status);
                edit(bill);
                iUserProfileRepository.save(userProfileCCDV);
                return "Xác nhận thành công";
            } else {
                return "Đơn đã được xác nhận";
            }
        } catch (Exception e) {
            return "không tìm thấy hóa đơn";
        }
    }

    @Override
    public String cancelBill(long idBill, Account cancelerAccount, String message) {   // thiếu message đi cùng
        try {
            Bill bill = iBillRepository.findById(idBill).get();
            UserProfile userProfile = iUserProfileRepository.getByAccount_Id(cancelerAccount.getId());
            if (bill.getStatus().getNameStatus().equals("wait")) {
                if (cancelerAccount.getRole().getNameRole().equals("ROLE_USER")) {
                    Status status = iStatusRepository.findById(7L).get(); // trạng thái 7 cancel from wait by user
                    bill.setStatus(status);
                    bill.setUserMessage(message);
                    edit(bill);
                    return "Bạn đã hủy thành công đơn hàng";
                } else if (cancelerAccount.getRole().getNameRole().equals("ROLE_CCDV")) {
                    Status status = iStatusRepository.findById(8L).get(); // trạng thái 8 cancel from wait by ccdv
                    bill.setStatus(status);
                    bill.setCcdvMessage(message);
                    edit(bill);
                    return "Bạn đã hủy thành công";
                }
            } else if (bill.getStatus().getNameStatus().equals("recevied")) {
                if (cancelerAccount.getRole().getNameRole().equals("ROLE_USER")) {
                    Status status = iStatusRepository.findById(9L).get(); // trạng thái 9 cancel from recevied by user
                    bill.setStatus(status);
                    bill.setUserMessage(message);
                    userProfile.setBalance((long) (userProfile.getBalance() + (bill.getTotal() * 0.75)));
                    edit(bill);
                    return "Bạn đã hủy thành công đơn hàng ";
                } else if (cancelerAccount.getRole().getNameRole().equals("ROLE_CCDV")) {
                    Status status = iStatusRepository.findById(10L).get(); // trạng thái 10 cancel from recevied by ccdv
                    bill.setStatus(status);
                    bill.setCcdvMessage(message);
                    edit(bill);
                    return "Bạn đã hủy thành công";
                }
            }
        } catch (Exception e) {
            return "Không tìm thấy hóa đơn";
        }
        return "Không tìm thấy hóa đơn";
    }

    @Override
    public Bill getLatestBillBy2Acc(Long ccdvId, Long userId) {
        try {
            List<Bill> results = entityManager.createQuery("select b from Bill b " +
                            "where b.accountCCDV.id = :ccdvId and b.accountUser.id = :userId " +
                            "and b.isActive = true and b.status.id = 6 " +
                            "order by b.id desc")
                    .setMaxResults(1)
                    .setParameter("ccdvId", ccdvId)
                    .setParameter("userId", userId)
                    .getResultList();
            return results.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Bill> getAllBills() {
        return iBillRepository.getAllBills();
    }


    @Override
    public List<Bill> getAllBillByAccountUser(long id) {
        return iBillRepository.getAllBillByAccountUser(id);
    }

    @Override
    public List<Bill> findBillByStatus(Long idStatus,String usernameCCDV,String usernameUser) {
       String usernameCCDv = "%" + usernameCCDV + "%";
       String usernameUser1 = "%" + usernameUser + "%";
       if (usernameCCDv ==" ") usernameCCDv= null;
       if (usernameUser1 ==" ") usernameUser1= null;
        return iBillRepository.findBillsByStatusIds(idStatus,usernameCCDv,usernameUser1);
    }


//    @Override
//    public Bill getBillAccountUserById(long idAccountUser, long idBill) {
//        return iBillRepository.getBillDetailByAccountUser(idAccountUser, idBill);
//    }


}
