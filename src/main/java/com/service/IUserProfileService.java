package com.service;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.model.dto.UserDTO;

import java.util.List;

public interface IUserProfileService extends ICrudService<UserProfile>{

    UserProfile getByAccountId(long id);
    List<UserDTO> getNewestCCDVs(int qty);
    List<UserDTO> getBySupplies(List<Supply> supplies);

}
