package com.service.ipml;

import com.model.Review;
import com.repository.IReviewRepository;
import com.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    IReviewRepository iReviewRepository;
    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public Review getById(long id) {
        return null;
    }

    @Override
    public Review create(Review review) {
        return null;
    }

    @Override
    public Review edit(Review review) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
