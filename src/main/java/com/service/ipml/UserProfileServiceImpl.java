package com.service.ipml;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;
import com.repository.IUserProfileRepository;
import com.service.GeneralService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IUserProfileRepository iUserProfileRepository;

    @Override
    public List<UserProfile> getAll() {
        return iUserProfileRepository.findAll();
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
    public UserProfile getByAccountId(long id) {
        return iUserProfileRepository.getByAccount_Id(id);
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
//        List<UserDTO> resultsDb = entityManager.createQuery("select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) " +
//                        "from UserProfile u, in (u.supplies) sup " +
//                        "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
//                        "where (u.account.role.id = 3) and (u.account.status.id = 1) and (sup in (:list)) " +
//                        "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
//                        "group by u.id ")
//                .setParameter("list", supplies)
//                .getResultList();
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
}
