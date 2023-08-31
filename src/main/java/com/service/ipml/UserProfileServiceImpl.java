package com.service.ipml;

import com.model.Comment;
import com.model.UserProfile;
import com.repository.IUserProfileRepository;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @Autowired
    IUserProfileRepository iUserProfileRepository;

    @Override
    public List<UserProfile> getAll() {
        return iUserProfileRepository.findAll();
    }

    @Override
    public UserProfile getById(long id) {
        Optional<UserProfile> userProfile = iUserProfileRepository.findById(id);
        if (userProfile.isPresent()) {
            return userProfile.get();
        } else {
            return null;
        }
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        return iUserProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile edit(UserProfile userProfile) {
        return iUserProfileRepository.save(userProfile);
    }

    @Override
    public void deleteById(long id) {
        iUserProfileRepository.deleteById(id);
    }

    @Override
    public UserProfile getByAccountId(long id) {
        return iUserProfileRepository.getByAccount_Id(id);
    }

    @Override
    public Optional<UserProfile> getUserProfileByAccount_Id(long id) {
        return iUserProfileRepository.getUserProfileByAccount_Id(id);
    }
}
