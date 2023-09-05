package com.service;

import com.model.UserProfile;

import java.util.List;

public interface IUserProfileService extends ICrudService<UserProfile>{

    UserProfile getByAccountId(long id);

    List<UserProfile> getUserHaveSameGender(String gender);
}
