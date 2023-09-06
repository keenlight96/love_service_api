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


    @GetMapping("/checkProfileExists/{id}")
    ResponseEntity<?> checkProfileExists(@PathVariable Long id) {
        Account account = iAccountService.getById(id);
        UserProfile existingProfile = iUserProfileService.getByAccountId(account.getId());
        return new ResponseEntity<>(existingProfile, HttpStatus.OK);
    }

    @PostMapping("/registerCCDV/{id}")
    ResponseEntity<UserProfile> createAccountCCDV(@PathVariable Long id, @RequestBody UserProfile userProfile) {
        Account account = iAccountService.getById(id);
        UserProfile existingProfile = iUserProfileService.getByAccountId(account.getId());

        if (existingProfile != null) {
            // Nếu đã tồn tại profile, thì bạn có thể cập nhật nó thay vì tạo mới

            existingProfile.setLastName(userProfile.getLastName());// Cập nhật thông tin của profile
            existingProfile.setFirstName(userProfile.getFirstName());// Cập nhật thông tin của profile
            existingProfile.setBirthday(userProfile.getBirthday());// Cập nhật thông tin của profile
            existingProfile.setCountry(userProfile.getCountry());// Cập nhật thông tin của profile
            existingProfile.setAddress(userProfile.getAddress());// Cập nhật thông tin của profile
            existingProfile.setBalance(userProfile.getBalance());// Cập nhật thông tin của profile
            existingProfile.setPhoneNumber(userProfile.getPhoneNumber());// Cập nhật thông tin của profile
            existingProfile.setPrice(userProfile.getPrice());// Cập nhật thông tin của profile
            existingProfile.setIdCard(userProfile.getIdCard());// Cập nhật thông tin của profile
            existingProfile.setGender(userProfile.getGender());// Cập nhật thông tin của profile
            existingProfile.setHeight(userProfile.getHeight());// Cập nhật thông tin của profile
            existingProfile.setWeight(userProfile.getWeight());// Cập nhật thông tin của profile
            existingProfile.setBasicRequest(userProfile.getBasicRequest());// Cập nhật thông tin của profile
            existingProfile.setFacebookLink(userProfile.getFacebookLink());// Cập nhật thông tin của profile
            iUserProfileService.edit(existingProfile); // Cập nhật thông tin trong cơ sở dữ liệu
            return new ResponseEntity<>(existingProfile, HttpStatus.OK);

        } else {
            // Nếu chưa có profile, thì tạo mới
            Role role = iRoleService.getById(3);
            account.setRole(role);
            Zone zone = iZoneService.getById(userProfile.getZone().getId());
            userProfile.setZone(zone);
            userProfile.setIsVIP(false);
            userProfile.setIsActive(true);
            userProfile.setAccount(account);
            iUserProfileService.create(userProfile); // Tạo mới profile
        }

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }


    @PostMapping("/registerAutoCCDV/{id}")
    ResponseEntity<UserProfile> registerAutoCCDV(@PathVariable Long id, @RequestBody UserProfile userProfile) {
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
    public ResponseEntity<List<UserDTO>> getRecentCCDVs(@PathVariable int qty) {
        return new ResponseEntity<>(iUserProfileService.getNewestCCDVs(qty), HttpStatus.OK);
    }

    @PostMapping("/searchBySupplies")
    public ResponseEntity<List<UserDTO>> searchBySupplies(@RequestBody List<Supply> supplies) {
        return new ResponseEntity<>(iUserProfileService.getBySupplies(supplies), HttpStatus.OK);
    }
}

