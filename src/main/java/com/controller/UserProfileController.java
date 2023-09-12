package com.controller;

import com.model.*;
import com.model.dto.*;
import com.model.pojo.ParamFilterUserProfile;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    IStatusService iStatusService;


    @GetMapping("/checkProfileExists/{id}")
    ResponseEntity<?> checkProfileExists(@PathVariable Long id) {
        Account account = iAccountService.getById(id);
        UserProfile existingProfile = iUserProfileService.getByAccountId(account.getId());
        return new ResponseEntity<>(existingProfile, HttpStatus.OK);
    }

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
        Account account = iAccountService.getById(id);
        UserProfile existingProfile = iUserProfileService.getByAccountId(account.getId());
        Role role = iRoleService.getById(3);
        List<Supply> supplies = userProfile.getSupplies();
        Zone zone = iZoneService.getById(userProfile.getZone().getId());

        account.setRole(role);
        if (existingProfile != null) {
            // Nếu đã tồn tại profile, cập nhật nó thay vì tạo mới
            existingProfile.setLastName(userProfile.getLastName());
            existingProfile.setFirstName(userProfile.getFirstName());
            existingProfile.setBirthday(userProfile.getBirthday());
            existingProfile.setCountry(userProfile.getCountry());
            existingProfile.setAddress(userProfile.getAddress());
            existingProfile.setBalance(userProfile.getBalance());
            existingProfile.setPhoneNumber(userProfile.getPhoneNumber());
            existingProfile.setPrice(userProfile.getPrice());
            existingProfile.setIdCard(userProfile.getIdCard());
            existingProfile.setGender(userProfile.getGender());
            existingProfile.setHeight(userProfile.getHeight());
            existingProfile.setWeight(userProfile.getWeight());
            existingProfile.setBasicRequest(userProfile.getBasicRequest());
            existingProfile.setFacebookLink(userProfile.getFacebookLink());
            existingProfile.setSupplies(supplies);
            existingProfile.setZone(zone);
            iUserProfileService.edit(existingProfile);
            return new ResponseEntity<>(existingProfile, HttpStatus.OK);
        } else {
            // Nếu chưa có profile, thì tạo mới
            account.setRole(role);
            userProfile.setZone(zone);
            userProfile.setIsVIP(false);
            userProfile.setIsActive(true);
            userProfile.setAccount(account);
            userProfile.setSupplies(supplies);
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
    @GetMapping("/get4MaleCCDVs/{qty}")
    public ResponseEntity<List<AccountCCDVDTO>> get4MaleCCDVs(@PathVariable int qty){
        return new ResponseEntity<>(iUserProfileService.get4MaleCCDVs(qty),HttpStatus.OK);
    }
    @GetMapping("/get8FemaleCCDVs/{qty}")
    public ResponseEntity<List<AccountCCDVDTO>> get8FemaleCCDVs(@PathVariable int qty){
        return new ResponseEntity<>(iUserProfileService.get8FemaleCCDVs(qty),HttpStatus.OK);
    }

    @GetMapping("/listCCDVHaveProperGender")
    ResponseEntity<List<UserDTO>> listCCDVHaveProperGender(@RequestParam Long id) {
        String gender = iUserProfileService.getByAccountId(id).getGender();
        List<UserDTO> listCCDV = iUserProfileService.getUserHaveProperGender(gender);
        return new ResponseEntity<>(listCCDV, HttpStatus.OK);
    }
    @PostMapping("/receiveMoney/{idBill}/{idAccountCCDV}")
    public ResponseEntity<?> receiveMoney(@PathVariable long idBill,@PathVariable long idAccountCCDV) {
        return new ResponseEntity<>(iUserProfileService.receiveMoney(idBill,idAccountCCDV),HttpStatus.OK);
    }
    @PostMapping("/filterByCCDv")
    public ResponseEntity<List<UserDTO>> getAllCCDVByFilter(@RequestBody FilterCCDV filterCCDV){
        System.out.println(filterCCDV.toString());
        List<UserDTO> userDTOList = iUserProfileService.getAllCCDVByFilter(filterCCDV);
        System.out.println(userDTOList);
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
}

