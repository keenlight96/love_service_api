package com.controller;

import com.model.*;
import com.model.dto.UserProfileIMG;
import com.service.ipml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/userDetail")
public class UserProfileController {
    @Autowired
    UserProfileServiceImpl userProfileService;
    @Autowired
    ImageServiceImpl imageService;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    InterestServiceImpl interestService;
    @Autowired
    BillServiceImpl billService;
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileIMG> getAll(@PathVariable long id){
        UserProfile userProfile = userProfileService.getUserProfileById(id);
        List<Image> img=imageService.getAllImageByAccountId(id);
        Account account = accountService.getById(id);
        List<Interest> interests = interestService.getAllInterestByAccountCCDV_Id(id);
        List<Bill> bills = billService.getAllByAccountCCDV_Id(id);
        UserProfileIMG userProfileIMG = new UserProfileIMG(userProfile,img,account,interests,bills);
        return new ResponseEntity<>(userProfileIMG, HttpStatus.OK);
    }
}
