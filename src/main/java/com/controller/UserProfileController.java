package com.controller;

import com.model.Account;
import com.model.Image;
import com.model.Interest;
import com.model.UserProfile;
import com.model.dto.UserProfileIMG;
import com.service.ipml.AccountServiceImpl;
import com.service.ipml.ImageServiceImpl;
import com.service.ipml.InterestServiceImpl;
import com.service.ipml.UserProfileServiceImpl;
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
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileIMG> getAll(@PathVariable long id){
        UserProfile userProfile = userProfileService.getUserProfileById(id);
        List<Image> img=imageService.getAllImageByAccountId(id);
        Account account = accountService.getById(id);
        Interest interest = interestService.getById(id);
        UserProfileIMG userProfileIMG = new UserProfileIMG(userProfile,img,account,interest);
//
//        if (userProfile != null) {
//            return new ResponseEntity(userProfile,HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        return new ResponseEntity<>(userProfileIMG, HttpStatus.OK);
    }
}
