package com.service;

import com.model.Review;

import java.util.List;

public interface IReviewService extends ICrudService<Review>{
    List<Review> getAllByAccountCCDV_UsernameAndIsActiveIsTrue(String username);
}
