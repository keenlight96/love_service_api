package com.service.ipml;

import com.model.Account;
import com.model.Message;
import com.model.dto.AccountMessageDTO;
import com.repository.IAccountRepository;
import com.repository.IBillRepository;
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
        return iAccountRepository.getAccountByUsernameAndPassword(username,password);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findByUsername(username).orElse(null);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }


    public Account activeAccount(String email){
        Account account = iAccountRepository.findAccountByEmail(email);
        account.setStatus(iStatusService.getById(1));
        return iAccountRepository.save(account);
    }
    public String emailActive(String email) {

        String to = email;
        String subject = "OTP Kích Hoạt";
        String content = "Xin Chào ...!\n" +
                "Bạn hoặc ai đó đã dùng email này để đăng ký tài khoản tại web Mrdunghr\n" +
                "\n" +

                "Nhấn vào Link này để kích hoạt nhanh: " +
                "http://localhost:8080/accounts/active-account?email="+email +
                "\n" +
                "--------------------------------------\n" +
                " + Phone  : (+84)382.564.626\n" +
                " + Email  : mrdunghr@gmail.com\n" +
                " + Address: Mông Dương - TP Cẩm Phả - Quảng Ninh\n";
        emailService.sendMail(to, subject, content);
        return content;

    }

}
