package com.service;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;
import com.model.dto.UserProfileFilterDTO;

import java.util.List;

import java.util.List;

import java.util.Optional;

public interface IUserProfileService extends ICrudService<UserProfile>{
        UserProfile getUserProfileById(long id);

    UserProfile getByAccountId(long id);
    Optional<UserProfile> getUserProfileByAccount_Id(long id);
    List<UserProfile> getTop6HotServiceProviders();
    List<UserProfileFilterDTO> getAllUserProfileByFilter(String first_name, String last_name, int birthday, String gender, String address, long views, String order);
    List<UserDTO> getNewestCCDVs(int qty);
    List<UserDTO> getBySupplies(List<Supply> supplies);
}
