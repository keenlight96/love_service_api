package com.controller;

import com.model.*;
import com.model.dto.UserProfileFilterDTO;
import com.model.pojo.ParamFilterUserProfile;
import com.model.dto.UserDTO;
import com.service.*;
import com.model.dto.UserProfileIMG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/userDetail")
public class UserProfileController {
    @Autowired
    IUserProfileService iUserProfileService;
    @Autowired
    IImageService iImageService;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IInterestService iInterestService;
    @Autowired
    IBillService iBillService;
    @Autowired
    DateService dateService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IZoneService iZoneService;
    @Autowired
    ISupplyService iSupplyService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileIMG> getAll(@PathVariable long id) {
        UserProfile userProfile = iUserProfileService.getUserProfileById(id);
        List<Image> img = iImageService.getAllImageByAccountId(id);
        Account account = iAccountService.getById(id);
        List<Interest> interests = iInterestService.getAllInterestByAccountCCDV_Id(id);
        List<Bill> bills = iBillService.getAllByAccountCCDV_Id(id);
        UserProfileIMG userProfileIMG = new UserProfileIMG(userProfile, img, account, interests, bills);
        return new ResponseEntity<>(userProfileIMG, HttpStatus.OK);
    }

    @GetMapping("/top6Service")
    public List<UserProfile> getTop6HotServiceProviders() {
        return iUserProfileService.getTop6HotServiceProviders();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<UserProfileFilterDTO>> getAllUserProfileByFilter(@RequestBody ParamFilterUserProfile paramFilterUserProfile){
        String firstName = paramFilterUserProfile.getFirstName();
        String lastName = paramFilterUserProfile.getLastName();
        int birthDay = paramFilterUserProfile.getBirthday();
        String  gender = paramFilterUserProfile.getGender();
        String address = paramFilterUserProfile.getAddress();
        long views = paramFilterUserProfile.getViews();
        String order = paramFilterUserProfile.getOrder();

        return new ResponseEntity<>(iUserProfileService.getAllUserProfileByFilter(firstName, lastName, birthDay, gender, address, views, order), HttpStatus.OK);
    }




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

    @GetMapping("/newestCCDVs/{qty}")
    public ResponseEntity<List<UserDTO>> getRecentCCDVs (@PathVariable int qty) {
        return new ResponseEntity<>(iUserProfileService.getNewestCCDVs(qty), HttpStatus.OK);
    }

    @PostMapping("/searchBySupplies")
    public ResponseEntity<List<UserDTO>> searchBySupplies(@RequestBody List<Supply> supplies) {
        return new ResponseEntity<>(iUserProfileService.getBySupplies(supplies), HttpStatus.OK);
    }
}

