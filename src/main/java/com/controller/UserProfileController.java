package com.controller;

import com.model.*;
import com.model.dto.UserDTO;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/userDetail")
public class UserProfileController {
    @Autowired
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
    @PostMapping("/registerAutoCCDV/{id}")
    ResponseEntity<UserProfile> registerAutoCCDV(@PathVariable Long id, @RequestBody UserProfile userProfile){
        Role role = iRoleService.getById(3);
        Account account = iAccountService.getById(id);
        account.setRole(role);
        Zone zone = iZoneService.getById(userProfile.getZone().getId());
        userProfile.setZone(zone);
        userProfile.setAccount(account);
        userProfile.setDateCreate(new Date());
        iUserProfileService.create(userProfile);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/newestCCDVs/{qty}")
    public ResponseEntity<List<UserDTO>> getRecentCCDVs (@PathVariable int qty) {
        return new ResponseEntity<>(iUserProfileService.getNewestCCDVs(qty), HttpStatus.OK);
    }

    @PostMapping("/searchBySupplies")
    public ResponseEntity<List<UserDTO>> searchBySupplies(@RequestBody List<Supply> supplies) {
        return new ResponseEntity<>(iUserProfileService.getBySupplies(supplies), HttpStatus.OK);
    }
}

