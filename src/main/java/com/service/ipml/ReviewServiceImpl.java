package com.service.ipml;

import com.model.Bill;
import com.model.Comment;
import com.model.Review;
import com.model.dto.UserDTO;
import com.repository.IReviewRepository;
import com.service.IBillService;
import com.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IReviewRepository iReviewRepository;
    @Autowired
    IBillService iBillService;

    @Override
    public List<Review> getAll() {
        return iReviewRepository.findAll();
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

    @Override
    public List<Review> getAllActiveByAccountCCDV_Username_Desc(String username) {
        return iReviewRepository.getAllActiveByAccountCCDV_Username_Desc(username);
    }

    @Override
    public Review getLatestReviewBy2Acc(Long ccdvId, Long userId) {
        try {
            List<Review> results = entityManager.createQuery("select r from Review r " +
                            "where r.accountCCDV.id = :ccdvId and r.accountUser.id = :userId " +
                            "order by r.id desc")
                    .setMaxResults(1)
                    .setParameter("ccdvId", ccdvId)
                    .setParameter("userId", userId)
                    .getResultList();
            return results.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean isAbleToReview(Long ccdvId, Long userId) {
        Bill latestBill = iBillService.getLatestBillBy2Acc(ccdvId, userId);
        Review latestReview = getLatestReviewBy2Acc(ccdvId, userId);
        if (latestBill == null) {
            return false;
        } else if (latestReview == null) {
            return true;
        } else {
            return latestBill.getDateCreate().getTime() - latestReview.getDate().getTime() > 0;
        }
    }
}
