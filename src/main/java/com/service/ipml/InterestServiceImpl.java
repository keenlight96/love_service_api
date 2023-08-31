package com.service.ipml;

import com.model.Comment;
import com.model.Image;
import com.model.Interest;
import com.repository.IInterestRepository;
import com.service.IInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestServiceImpl implements IInterestService {
    @Autowired
    IInterestRepository iInterestRepository;
    @Override
    public List<Interest> getAll() {
        return iInterestRepository.findAll();
    }

    @Override
    public Interest getById(long id) {
        Optional<Interest> interest = iInterestRepository.findById(id);
        if (interest.isPresent()) {
            return interest.get();
        } else {
            return null;
        }
    }

    @Override
    public Interest create(Interest interest) {
        return iInterestRepository.save(interest);
    }

    @Override
    public Interest edit(Interest interest) {
        return iInterestRepository.save(interest);
    }

    @Override
    public void deleteById(long id) {
        iInterestRepository.deleteById(id);
    }

    public List<Interest> getAllInterestsById(long id) {
        return iInterestRepository.getInterestsById(id);
    }
}
