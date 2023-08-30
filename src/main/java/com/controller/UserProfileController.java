package com.controller;

import com.model.UserProfile;
import com.service.IUserProfileService;
import com.service.ipml.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/userDetail")
public class UserProfileController {
    @Autowired
    UserProfileServiceImpl userProfileService;
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAll(){
        return new ResponseEntity<>(userProfileService.getAll(), HttpStatus.ACCEPTED);
    }
}
