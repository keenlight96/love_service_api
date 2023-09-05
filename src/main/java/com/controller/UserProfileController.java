package com.controller;

import com.model.*;
import com.model.pojo.FilterUserProfile;
import com.service.*;
import com.model.*;
import com.model.dto.UserProfileIMG;
import com.service.ipml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
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

    @GetMapping("/userDetail/{id}")
    public ResponseEntity<UserProfileIMG> getAll(@PathVariable long id) {
        UserProfile userProfile = userProfileService.getUserProfileById(id);
        List<Image> img = imageService.getAllImageByAccountId(id);
        Account account = accountService.getById(id);
        List<Interest> interests = interestService.getAllInterestByAccountCCDV_Id(id);
        List<Bill> bills = billService.getAllByAccountCCDV_Id(id);
        UserProfileIMG userProfileIMG = new UserProfileIMG(userProfile, img, account, interests, bills);
        return new ResponseEntity<>(userProfileIMG, HttpStatus.OK);
    }

    @GetMapping("/top6Service")
    public List<UserProfile> getTop6HotServiceProviders() {
        return userProfileService.getTop6HotServiceProviders();
    }

//    @PostMapping("/filter")
//    public ResponseEntity<List<UserProfile>> getAllUserProfileByFilter(@RequestBody FilterUserProfile filterUserProfile){
//        String firstName = filterUserProfile.getFirstName();
//        String lastName = filterUserProfile.getLastName();
//        int birthDay = filterUserProfile.getBirthday();
//        String  gender = filterUserProfile.getGender();
//        String address = filterUserProfile.getAddress();
//        long views = filterUserProfile.getViews();
//        Bill bill = filterUserProfile.getBill();
//        UserProfile(firstName,lastName,birthDay,gender,address,views,bill);
//       return new ResponseEntity<>(userProfile,HttpStatus.OK);
//    }


    DateService dateService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IUserProfileService iUserProfileService;
    @Autowired
    IZoneService iZoneService;
    @Autowired
    ISupplyService iSupplyService;

    @PostMapping("/registerCCDV/{id}")
    ResponseEntity<UserProfile> createAccountCCDV(@PathVariable Long id, @RequestBody UserProfile userProfile) {
        Role role = iRoleService.getById(3);
        Account account = iAccountService.getById(id);
        account.setRole(role);
        Zone zone = iZoneService.getById(userProfile.getZone().getId());
        userProfile.setZone(zone);
        userProfile.setIsVIP(false);
        userProfile.setIsActive(true);
        userProfile.setAccount(account);
        iUserProfileService.create(userProfile);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    @PostMapping("/registerCCDVs/{id}")
    ResponseEntity<UserProfile> getUserProfile(@PathVariable Long id){
        UserProfile userProfile1 = iUserProfileService.getByAccountId(id);
      return new ResponseEntity<>(userProfile1,HttpStatus.OK);
    }
}
