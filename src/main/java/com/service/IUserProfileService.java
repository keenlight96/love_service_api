package com.service;

import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;

import java.util.List;

import java.util.Optional;

public interface IUserProfileService extends ICrudService<UserProfile>{

    UserProfile getByAccountId(long id);
    Optional<UserProfile> getUserProfileByAccount_Id(long id);
    List<UserDTO> getNewestCCDVs(int qty);
    List<UserDTO> getBySupplies(List<Supply> supplies);
}
