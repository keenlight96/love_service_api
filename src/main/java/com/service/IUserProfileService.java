package com.service;

import com.model.UserProfile;

public interface IUserProfileService extends ICrudService<UserProfile>{

    UserProfile getByAccountId(long id);
}
