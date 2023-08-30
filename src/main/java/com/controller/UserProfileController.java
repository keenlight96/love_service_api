package com.controller;

import com.model.UserProfile;
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
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAll(){
        return new ResponseEntity<>(userProfileService.getAll(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserProfile>> findOne(@PathVariable long id) {
        return new ResponseEntity<>(userProfileService.findOne(id), HttpStatus.OK);
    }
}
