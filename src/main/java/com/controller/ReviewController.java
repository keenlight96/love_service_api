package com.controller;

import com.model.Review;
import com.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    IReviewService iReviewService;

    @GetMapping("/allActiveByCCDV_Username/{username}")
    ResponseEntity<List<Review>> getAllByAccountCCDV_UsernameAndIsActiveIsTrue(@PathVariable String username) {
        return new ResponseEntity<>(iReviewService.getAllByAccountCCDV_UsernameAndIsActiveIsTrue(username), HttpStatus.OK);
    }
}
