package com.service;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface IUserProfileService extends ICrudService<UserProfile>{
    UserProfile getUserProfileById(long id);
    UserProfile getByAccountId(long id);
    List<UserDTO> getUserHaveProperGender(String gender);
    Optional<UserProfile> getUserProfileByAccount_Id(long id);
    List<UserDTO> getTopServiceProviders(int qty);
    List<UserProfileFilterDTO> getAllUserProfileByFilter(String first_name, String last_name, int birthday, String gender, String address, long views, String order);
    List<UserDTO> getNewestCCDVs(int qty);
    List<UserDTO> getBySupplies(List<Supply> supplies);
    List<AccountCCDVDTO> get4MaleCCDVs(int qty);
    List<AccountCCDVDTO> get8FemaleCCDVs(int qty);
    String receiveMoney(long idBill,long idAccountCCDV);
    List<UserDTO> getAllCCDVByFilter(FilterCCDV filterCCDV);
    String increaseView(long id);

    @Query(value = "select new com.model.dto.UserDTO(u, '', avg(rev.rating), count(rev.rating)) from UserProfile u " +
            "left outer join Review rev on rev.accountCCDV.id = u.account.id " +
            "where (u.account.role.id = 3) and (u.account.status.id = 1) " +
            "and (u.isActive = true) and (u.account.isActive = true) and (rev.isActive = true or rev is null) " +
            "and (u.gender = :gender) and (u.zone.zone = :zone) " +
            "and (u.price >= :lowAge and u.price <= :highPrice) " +
            "group by u.id " +
            "order by u.id desc")
    List<AccountDTO> getAllAccountUserFilter(@Param("gender") String gender, @Param("zone") String zone
            , @Param("lowPrice") Long lowPrice, @Param("highPrice") Long highPrice);
    List<Supply> getSuppliesByIdUser(long id);
}
