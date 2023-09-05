package com.service;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;

import java.util.List;

import java.util.List;

public interface IUserProfileService extends ICrudService<UserProfile>{
        UserProfile getUserProfileById(long id);

    UserProfile getByAccountId(long id);
    List<UserProfile> getTop6HotServiceProviders();
    List<UserProfile> getAllUserProfileByFilter(String first_name, String last_name, int birthday, String gender, String address, long views);

    List<UserDTO> getNewestCCDVs(int qty);
    List<UserDTO> getBySupplies(List<Supply> supplies);
}
