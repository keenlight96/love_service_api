package com.service.ipml;

import com.model.Account;
import com.model.Supply;
import com.model.Comment;
import com.model.*;
import com.model.dto.*;
import com.repository.IBillRepository;
import com.repository.IStatusRepository;
import com.repository.IUserProfileRepository;
import com.service.GeneralService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IUserProfileRepository iUserProfileRepository;
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    IStatusRepository iStatusRepository;

    @Override
    public List<UserProfile> getAll() {
        return iUserProfileRepository.findAll();
    }

    public Optional<UserProfile> findOne(long id) {
        return iUserProfileRepository.findById(id);
    }

    @Override
    public UserProfile getById(long id) {
        Optional<UserProfile> userProfile = iUserProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            return userProfile.get();
        } else {
            return null;
        }
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        return iUserProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile edit(UserProfile userProfile) {
        return iUserProfileRepository.save(userProfile);
    }

    @Override
    public void deleteById(long id) {
        iUserProfileRepository.deleteById(id);
    }

    @Override
    public UserProfile getUserProfileById(long id) {
        return iUserProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfile getByAccountId(long id) {
        return iUserProfileRepository.getByAccount_Id(id);
    }

    @Override
    public Optional<UserProfile> getUserProfileByAccount_Id(long id) {
        return iUserProfileRepository.getUserProfileByAccount_Id(id);
    }

    @Override
    public List<UserDTO> getUserHaveProperGender(String gender) {
        List<UserDTO> rs = entityManager.createQuery(" select new com.model.dto.UserDTO(up,'',avg(rev.rating),count(rev.rating))from UserProfile up" +
                        " left outer join Review rev on rev.accountCCDV.id = up.account.id " +
                        " where (up.account.role.id = 3) and (up.account.status.id = 1)" +
                        " and (up.isActive = true) and (up.account.isActive = true) and" +
                        " (up.gender != :gender) " +
                        "group by up.id " +
                        " order by up.dateCreate desc  ")
                .setParameter("gender", gender)
                .setMaxResults(5)
                .getResultList();
        rs.addAll(entityManager.createQuery(" select new com.model.dto.UserDTO(up,'',avg(rev.rating),count(rev.rating))from UserProfile up" +
                        " left outer join Review rev on rev.accountCCDV.id = up.account.id " +
                        " where (up.account.role.id = 3) and (up.account.status.id = 1)" +
                        " and (up.isActive = true) and (up.account.isActive = true) and" +
                        " (up.gender != :gender) " +
                        "group by up.id " +
                        " order by up.dateCreate asc  ")
                .setParameter("gender", gender)
                .setMaxResults(5)
                .getResultList());
        for (UserDTO userDto :
                rs) {
            userDto.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(userDto.getUserProfile().getSupplies(), 3)));
        }
        return rs;
    }


    @Override
    public List<UserDTO> getNewestCCDVs(int qty) {
        List<UserDTO> results = entityManager.createQuery("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) from UserProfile u " +
                        "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
                        "where (u.account.role.id = 3) and (u.account.status.id = 1) " +
                        "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
                        "group by u.id " +
                        "order by u.id desc")
                .setMaxResults(qty)
                .getResultList();

        for (UserDTO userDto :
                results) {
            userDto.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(userDto.getUserProfile().getSupplies(), 3)));
        }
        return results;
    }

    @Override
    public List<UserDTO> getBySupplies(List<Supply> supplies) {
        List<UserDTO> results = new ArrayList<>();
        List<UserDTO> resultsDb = iUserProfileRepository.getBySupplies(supplies);

        Set<Long> supplySet = supplies.stream().map(Supply::getId).collect(Collectors.toSet());

        for (UserDTO userDto :
                resultsDb) {
            List<Long> idList = userDto.getUserProfile().getSupplies().stream().map(Supply::getId).collect(Collectors.toList());
            if (new HashSet<>(idList).containsAll(supplySet)) {
                userDto.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(userDto.getUserProfile().getSupplies(), 3)));
                results.add(userDto);
            }
        }
        return results;
    }

    @Override
    public List<UserDTO> getTopServiceProviders(int qty) {
        List<UserDTO> results = entityManager.createQuery("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) from UserProfile u " +
                        "join Review rev on rev.accountCCDV.id = u.account.id " +
                        "where (u.account.role.id = 3) and (u.account.status.id = 1) " +
                        "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true) " +
                        "group by u.id " +
                        "order by u.views desc")
                .setMaxResults(qty)
                .getResultList();

        for (UserDTO userDto :
                results) {
            userDto.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(userDto.getUserProfile().getSupplies(), 3)));
        }
        return results;
    }

    @Override
    public List<UserProfileFilterDTO> getAllUserProfileByFilter(String first_name, String last_name, int birthday, String gender, String address, long views, String order) {
        return iUserProfileRepository.getAllUserProfileByFilter(first_name, last_name, birthday, gender, address, views, order);
    }

    @Override
    public List<AccountCCDVDTO> get4MaleCCDVs(int qty) {
        List<AccountCCDVDTO> accountMaleCCDVs = entityManager.createQuery("SELECT new com.model.dto.AccountCCDVDTO(u, a, '', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
                        "FROM UserProfile u " +
                        "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
                        "LEFT JOIN Account a ON a.id = u.account.id " +
                        "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
                        "WHERE (u.account.role.id = 3) " +
                        "AND (u.account.status.id = 1) " +
                        "AND (b.isActive = true) " +
                        "AND (b.status.id = 6) " +
                        "AND (u.account.isActive = true) " +
                        "AND (rev.isActive = true OR rev IS NULL) " +
                        "AND (u.gender = 'nam')" +
                        "GROUP BY u.id " +
                        "ORDER BY COUNT(DISTINCT b.id) DESC")
                .setMaxResults(qty)
                .getResultList();
        for (AccountCCDVDTO accountCCDVDTO : accountMaleCCDVs) {
            accountCCDVDTO.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(accountCCDVDTO.getUserProfile().getSupplies(), 3)));
        }
        return accountMaleCCDVs;
    }

    @Override
    public List<AccountCCDVDTO> get8FemaleCCDVs(int qty) {
        List<AccountCCDVDTO> account8FemaleCCDVs = entityManager.createQuery("SELECT new com.model.dto.AccountCCDVDTO(u, a, '', AVG(rev.rating), COUNT( rev.rating), COUNT(DISTINCT b.id)) " +
                        "FROM UserProfile u " +
                        "LEFT JOIN Review rev ON rev.accountCCDV.id = u.account.id " +
                        "LEFT JOIN Account a ON a.id = u.account.id " +
                        "LEFT JOIN Bill b ON b.accountCCDV.id = a.id " +
                        "WHERE (u.account.role.id = 3) " +
                        "AND (u.account.status.id = 1) " +
                        "AND (b.isActive = true) " +
                        "AND (b.status.id = 6) " +
                        "AND (u.account.isActive = true) " +
                        "AND (rev.isActive = true OR rev IS NULL) " +
                        "AND (u.gender = 'nữ')" +
                        "GROUP BY u.id " +
                        "ORDER BY COUNT(DISTINCT b.id) DESC")
                .setMaxResults(qty)
                .getResultList();
        for (AccountCCDVDTO accountCCDVDTO : account8FemaleCCDVs) {
            accountCCDVDTO.setRandomServices(GeneralService.toStringOfSupplies(GeneralService.getRandomItems(accountCCDVDTO.getUserProfile().getSupplies(), 3)));
        }
        return account8FemaleCCDVs;
    }

    @Override
    public String receiveMoney(long idBill, long idAccountCCDV) {
        Optional<UserProfile> optionalUserProfile = getUserProfileByAccount_Id(idAccountCCDV);
        Optional<Bill> bill = iBillRepository.findById(idBill);
        Optional<Status> status = iStatusRepository.findById(Long.valueOf(6)); // trạng thái báo cáo
        if (bill.get().getStatus().equals("reporting")) {
            optionalUserProfile.get().setBalance(optionalUserProfile.get().getBalance() + bill.get().getTotal());
            bill.get().setStatus(status.get());
            edit(optionalUserProfile.get());
            iBillRepository.save(bill.get());
            return "Nhận tiền thành công";
        } else {
            return "bạn đã nhận tiền rồi";
        }
    }

    @Override
    public List<UserDTO> getAllCCDVByFilter(FilterCCDV filterCCDV) {
        String nickname = "%" + filterCCDV.getNickname() + "%" ;
        String zone = "%" + filterCCDV.getZone() + "%";
        String gender = "%" + filterCCDV.getGender() + "%" ;
        Integer birthday = filterCCDV.getYear();
        Long minPrice = filterCCDV.getMinPrice();
        Long maxPrice = filterCCDV.getMaxPrice();
        if(nickname ==" ") nickname = null;
        if(zone ==" ") zone = null;
        if(gender ==" ") gender = null;
        return iUserProfileRepository.getAllCCDVByFilter(nickname, zone, gender, birthday,minPrice,maxPrice);
    }

    @Override
    public String increaseView(long id) {
        UserProfile userProfile = iUserProfileRepository.findById(id).get();
        if (userProfile.getIsActive()) {
            userProfile.setViews((userProfile.getViews() + 1));
            iUserProfileRepository.save(userProfile);
            return "+1";
        }
        return "bug";
    }

    @Override
    public List<AccountDTO> getAllAccountUserFilter(String gender, String zone, Long lowPrice, Long highPrice) {
        return null;
    }
}
