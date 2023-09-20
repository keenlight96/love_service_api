package com.service.ipml;

import com.model.Account;
import com.model.Image;
import com.model.Message;
import com.model.Status;
import com.model.dto.AccountDTO;
import com.model.dto.AccountMessageDTO;
import com.model.dto.FilterAccountByStatusDTO;
import com.model.dto.ImageDTO;
import com.repository.IAccountRepository;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.service.IAccountService;
import com.service.IStatusService;
import com.service.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class AccountServiceImpl implements IAccountService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    IStatusService iStatusService;
    @Autowired
    IStatusRepository iStatusRepository;

    @Override
    public List<Account> getAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account getById(long id) {
        Optional<Account> account = iAccountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            return null;
        }
    }

    @Override
    public Account create(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public Account edit(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public void deleteById(long id) {
        iAccountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Account findActiveByUsername(String username) {
        return iAccountRepository.findActiveByUsername(username);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }

    @Override
    public Optional<Account> login(String username, String password) {
        return iAccountRepository.getAccountByUsernameAndPassword(username, password);
    }

//    @Override
//    public Optional<Account> getByProviderUserId(long id) {
//        return (Optional<Account>) iBillRepository.getAccount_User_IdAndBill_IdByAccount_CCDV_Id(id);
//    }


    @Override
    public List<AccountMessageDTO> getAllMessageReceiversByAccountId(long id) {
        List<AccountMessageDTO> rs = iAccountRepository.getAllMessageReceiversByAccountId(id);
        for (AccountMessageDTO account :
                rs) {
            List<Message> messages = entityManager.createQuery("select m from Message m " +
                    "where (m.sender.id = :accId or m.receiver.id = :accId) order by m.id desc")
                    .setParameter("accId", account.getId())
                    .setMaxResults(1)
                    .getResultList();
            account.setLastMessage(messages.get(0));
        }

        rs.sort((o1, o2) -> (int) (o2.getLastMessage().getId() - o1.getLastMessage().getId()));
        return rs;
    }



    @Override
    public String workOrRest(long id) {
        Account account = iAccountRepository.findById(id).get();
        if (account.getRole().getId() == 3 && account.getStatus().getId() == 1) {
            account.setStatus(iStatusRepository.findById(111L).get());
            iAccountRepository.save(account);
            return "Bạn đã tắt chức năng cung cấp dịch vụ";
        }else if(account.getRole().getId() == 3 && account.getStatus().getId() == 111){
            account.setStatus(iStatusRepository.findById(1L).get());
            iAccountRepository.save(account);
            return "Bạn đã bật chức năng cung cấp dịch vụ";
        }
        return "Check lại code vì chỉ có tk bị block mới vào đây";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findByUsername(username).orElse(null);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }


    public Account activeAccount(String email) {
        Account account = iAccountRepository.findAccountByEmail(email);
        account.setStatus(iStatusService.getById(3));
        return iAccountRepository.save(account);
    }

    public String emailActive(String email) {

        String to = email;
        String subject = "OTP Kích Hoạt";
        String content = "Xin Chào ...!\n" +
                "Bạn hoặc ai đó đã dùng email này để đăng ký tài khoản tại web thuenguoiyeu.com.vn\n" +
                "\n" +

                "Nhấn vào Link này để kích hoạt nhanh: " +
                "http://localhost:8080/accounts/active-account?email="+email +"&source=email_activation" +
                "\n" +
                "--------------------------------------\n" +
                " + Phone  : (+84)382.564.626\n" +
                " + Email  : thuenguoiyeu@gmail.com\n" +
                " + Address: Hà nội\n";
        emailService.sendMail(to, subject, content);
        return content;

    }
    @Override
    public List<Account> getAllUserAc() {
        List<Account> accountList = iAccountRepository.getAllUserAc();
        return accountList;
    }
    @Override
    public String blockAccount(Long idAccount){
        try {
            Account account1 = iAccountRepository.findById(idAccount).get();
            Status status = iStatusService.getById(2L);
            account1.setStatus(status);
            edit(account1);
            return "Khóa thành công ti khoản";
        }catch (Exception e){
            return "không tìm thấy tài khoản";
        }
    }

    @Override
    public List<Account> getAllCCDVAc() {
        return iAccountRepository.getAllCCDVAc();
    }

    @Override
    public List<AccountDTO> getAllAccountUserFilter(FilterAccountByStatusDTO filterAccountByStatusDTO) {
        try {
            String username = "%" + filterAccountByStatusDTO.getUsername() + "%";
            String status =  filterAccountByStatusDTO.getStatus() ;
            if (username == "") username = null;
            if (status == "") status = null;
            List<AccountDTO> accountDTOList = iAccountRepository.getAllAccountUserFilter(status,username);
            return accountDTOList;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<AccountDTO> getAllAccountCCDVFilter(FilterAccountByStatusDTO filterAccountByStatusDTO ) {
        try {
            String username = "%" + filterAccountByStatusDTO.getUsername() + "%";
            String status =  filterAccountByStatusDTO.getStatus() ;
            if (username == "") username = null;
            if (status == "") status = null;
            List<AccountDTO> accountDTOList = iAccountRepository.getAllAccountCCDVFilter(status,username);
            return accountDTOList;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Account> getAccountCCDVRegister( ) {
        List<Account> accountCCDVRegisterList = iAccountRepository.getAccountCCDVRegister();
        return accountCCDVRegisterList;
    }

    @Override
    public String activeCCDV(String username) {
        Account account = iAccountRepository.findByUsername(username).get();
        Status status = iStatusService.getById(1L);
        account.setStatus(status);
        edit(account);
        return "kích hoạt tài khoản thành công";
    }
}
