package com.service;

import com.model.UserProfile;

import java.util.Optional;

public interface IUserProfileService extends ICrudService<UserProfile>{

    UserProfile getByAccountId(long id);
    Optional<UserProfile> getUserProfileByAccount_Id(long id);
}
