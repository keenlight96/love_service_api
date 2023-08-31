package com.service;

import com.model.UserProfile;

public interface IUserProfileService extends ICrudService<UserProfile>{
        UserProfile getUserProfileById(long id);

    UserProfile getByAccountId(long id);
}
