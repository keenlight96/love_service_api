package com.service.ipml;

import com.model.Comment;
import com.model.Review;
import com.repository.IReviewRepository;
import com.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Review> review = iReviewRepository.findById(id);
        if (review.isPresent()) {
            return review.get();
        } else {
            return null;
        }
    }

    @Override
    public Review create(Review review) {
        return iReviewRepository.save(review);
    }

    @Override
    public Review edit(Review review) {
        return iReviewRepository.save(review);
    }

    @Override
    public void deleteById(long id) {
        iReviewRepository.deleteById(id);
    }
}
