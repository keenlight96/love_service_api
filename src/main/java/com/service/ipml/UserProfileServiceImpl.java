package com.service.ipml;

import com.model.UserProfile;
import com.repository.IUserProfileRepository;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @Autowired
    IUserProfileRepository iUserProfileRepository;

    @Override
    public List<UserProfile> getAll() {
        return null;
    }

    @Override
    public UserProfile getById(long id) {
        return null;
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        return null;
    }

    @Override
    public UserProfile edit(UserProfile userProfile) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
