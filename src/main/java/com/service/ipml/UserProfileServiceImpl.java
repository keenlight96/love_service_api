package com.service.ipml;

import com.model.Comment;
import com.model.Supply;
import com.model.UserProfile;
import com.repository.IUserProfileRepository;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @Autowired
    IUserProfileRepository iUserProfileRepository;

    @Override
    public List<UserProfile> getAll() {
        return iUserProfileRepository.findAll();
    }

    public Optional<UserProfile> findOne(long id) {
        return iUserProfileRepository.findById(id);
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
    public UserProfile getUserProfileById(long id) {
        return iUserProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfile getByAccountId(long id) {
        return iUserProfileRepository.getByAccount_Id(id);
    }

    @Override
    public List<UserProfile> getTop6HotServiceProviders() {
        Random random = new Random();
        List<UserProfile> userProfiles = iUserProfileRepository.getTop6HotServiceProviders();
        for (int i = 0; i < userProfiles.size(); i++) {
            List<Supply> supplies = new ArrayList<>();
            List<Supply> supplies1 = userProfiles.get(i).getSupplies();
            List<Integer> uniqueIndexes = new ArrayList<>();
            while (uniqueIndexes.size() < 3 && uniqueIndexes.size() < supplies1.size()) {
                int randomIndex = random.nextInt(supplies1.size());
                if (!uniqueIndexes.contains(randomIndex)) {
                    uniqueIndexes.add(randomIndex);
                }
            }
            for (int j = 0; j < uniqueIndexes.size(); j++) {
                int randomIndex = uniqueIndexes.get(j);
                supplies.add(supplies1.get(randomIndex));
            }
            userProfiles.get(i).setSupplies(supplies);
        }
        return userProfiles;
    }

    @Override
    public List<UserProfile> getAllUserProfileByFilter(String first_name, String last_name, int birthday, String gender, String address, long views) {
        return iUserProfileRepository.getAllUserProfileByFilter(first_name, last_name, birthday, gender, address, views);
    }
}
