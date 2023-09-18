package com.service;

import com.model.Review;

import java.util.List;

public interface IReviewService extends ICrudService<Review>{
    List<Review> getAllActiveByAccountCCDV_Username_Desc(String username);
    Review getLatestReviewBy2Acc(Long ccdvId, Long userId);
    Boolean isAbleToReview(Long ccdvId, Long userId);
}
