package com.controller;


import com.model.dto.AccountMessageDTO;
import com.model.dto.InformationDTO;
import com.repository.IBillRepository;
import com.model.Account;
import com.model.Role;
import com.model.Status;
import com.model.*;
import com.model.dto.AccountRegisterDTO;
import com.model.messageErorr.ValidStatus;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;


@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id){
        Account account = iAccountService.getById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @Autowired
    IBillRepository iBillRepository;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IStatusService iStatusService;

    @Autowired
    IUserProfileService iUserProfileService;
    @GetMapping("/account-profile/{id}")
    ResponseEntity<?> getAccount(@PathVariable Long id){
        Account account = iAccountService.getById(id);
        UserProfile userProfile = iUserProfileService.getByAccountId(account.getId());
        InformationDTO informationDTO = new InformationDTO();
        informationDTO.setAvatar(account.getAvatar());
        informationDTO.setEmail(account.getEmail());
        informationDTO.setNickname(account.getNickname());
        informationDTO.setFirstName(userProfile.getFirstName());
        informationDTO.setLastName(userProfile.getLastName());
        informationDTO.setBirthday(userProfile.getBirthday());
        informationDTO.setCountry(userProfile.getCountry());
        informationDTO.setAddress(userProfile.getAddress());
        informationDTO.setPhoneNumber(userProfile.getPhoneNumber());
        informationDTO.setGender(userProfile.getGender());
        informationDTO.setHeight(userProfile.getHeight());
        informationDTO.setWeight(userProfile.getWeight());
        informationDTO.setDescribes(userProfile.getDescribes());
        informationDTO.setBasicRequest(userProfile.getBasicRequest());
        informationDTO.setFacebookLink(userProfile.getFacebookLink());
        informationDTO.setSupplies(userProfile.getSupplies());
        informationDTO.setZone(userProfile.getZone());

        return new ResponseEntity<>(informationDTO,HttpStatus.OK);
    }
    @GetMapping("/profile/{id}")
    ResponseEntity<?> getProfileByAccountLogin(@PathVariable Long id){
        Account account =iAccountService.getById(id);
        UserProfile userProfile = iUserProfileService.getById(account.getId());
        return new ResponseEntity<>(userProfile,HttpStatus.OK);
    }

    @GetMapping("/active-account")
    public ResponseEntity<?> activeAccount(@RequestParam String email){
        iAccountService.activeAccount(email);
        String frontendLoginUrl = "http://localhost:3000/login"; // Thay thế bằng URL của trang login frontend của bạn
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", frontendLoginUrl).build();
    }

    @PostMapping("/changeAvatar/{id}")
    ResponseEntity<?> changeAvatarInProfileByUserLogin(@PathVariable Long id,@RequestBody Account account){
        Account account1 = iAccountService.getById(id);
        account1.setAvatar(account.getAvatar());
        iAccountService.edit(account1);
        return new ResponseEntity<>(account1,HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    ResponseEntity<AccountRegisterDTO> createAccountUser(@RequestBody AccountRegisterDTO accountDTO) {
        if(iAccountService.findByUsername(accountDTO.getUsername()).isPresent() && iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED_EMAIL_EXIST),HttpStatus.OK);
        }
        if (iAccountService.findByUsername(accountDTO.getUsername()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED),HttpStatus.OK);
        }
        if (iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.EMAIL_EXIST),HttpStatus.OK);
        }
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
//        account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        account.setNickname(accountDTO.getNickName());
        accountDTO.setAvatar("https://cdn0.iconfinder.com/data/icons/avatar-basic-colors-doodle-1/91/Avatar__Basic_Doodle_C-42-512.png");
        account.setAvatar(accountDTO.getAvatar());

        Role role = iRoleService.findByName("ROLE_USER");
        accountDTO.setRole(role);
        account.setRole(accountDTO.getRole());
        Status status = iStatusService.getById(333);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);
        iAccountService.create(account);
//        iAccountService.emailActive(account.getEmail());
        long accountId = account.getId();
        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL,accountId), HttpStatus.OK);
    }

    @PostMapping("/registerUserGoogle")
    ResponseEntity<AccountRegisterDTO> createAccountUserGoogle(@RequestBody AccountRegisterDTO accountDTO) {
        if(iAccountService.findByUsername(accountDTO.getUsername()).isPresent() && iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED_EMAIL_EXIST),HttpStatus.OK);
        }
        if (iAccountService.findByUsername(accountDTO.getUsername()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED),HttpStatus.OK);
        }
        if (iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.EMAIL_EXIST),HttpStatus.OK);
        }
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setNickname(accountDTO.getNickName());
        account.setAvatar(accountDTO.getAvatar());

        int max = 999999;
        int min = 10000;
        int range = max - min + 1;
        account.setPassword("g" + ((int)(Math.random() * range) + min));

        Role role = iRoleService.findByName("ROLE_USER");
        accountDTO.setRole(role);
        account.setRole(accountDTO.getRole());
        Status status = iStatusService.getById(1);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);
        iAccountService.create(account);
        long accountId = account.getId();

        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL, accountId), HttpStatus.OK);
    }

    @PostMapping("/registerUserAndProfile")
    ResponseEntity<AccountRegisterDTO> createAccountUserAndProfile(@RequestBody AccountRegisterDTO accountDTO) {
        if(iAccountService.findByUsername(accountDTO.getUsername()).isPresent() && iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED_EMAIL_EXIST),HttpStatus.OK);

        }
        if (iAccountService.findByUsername(accountDTO.getUsername()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED),HttpStatus.OK);
        }
        if (iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.EMAIL_EXIST),HttpStatus.OK);
        }
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setNickname(accountDTO.getNickName());
        accountDTO.setAvatar("https://cdn0.iconfinder.com/data/icons/avatar-basic-colors-doodle-1/91/Avatar__Basic_Doodle_C-42-512.png");
        account.setAvatar(accountDTO.getAvatar());
        Role role = iRoleService.findByName("ROLE_USER");
        accountDTO.setRole(role);
        account.setRole(accountDTO.getRole());
        Status status = iStatusService.getById(333);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);
        iAccountService.create(account);
        iAccountService.emailActive(account.getEmail());
        long id = account.getId();
        UserProfile userProfile = new UserProfile();
        userProfile.setAccount(iAccountService.getById(id));
        userProfile.setDateCreate(new Date());
        userProfile.setIsActive(true);
        iUserProfileService.create(userProfile);

        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL), HttpStatus.OK);
    }

    @PostMapping("/registerUserAndProfileGoogle")
    ResponseEntity<AccountRegisterDTO> createAccountUserAndProfileGoogle(@RequestBody AccountRegisterDTO accountDTO) {
        if(iAccountService.findByUsername(accountDTO.getUsername()).isPresent() && iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED_EMAIL_EXIST),HttpStatus.OK);
        }
        if (iAccountService.findByUsername(accountDTO.getUsername()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED),HttpStatus.OK);
        }
        if (iAccountService.findByEmail(accountDTO.getEmail()).isPresent()){
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.EMAIL_EXIST),HttpStatus.OK);
        }
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setNickname(accountDTO.getNickName());
        account.setAvatar(accountDTO.getAvatar());

        int max = 999999;
        int min = 10000;
        int range = max - min + 1;
        account.setPassword("g" + ((int)(Math.random() * range) + min));

        Role role = iRoleService.findByName("ROLE_USER");
        accountDTO.setRole(role);
        account.setRole(accountDTO.getRole());
        Status status = iStatusService.getById(1);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);
        iAccountService.create(account);
        long id = account.getId();
        UserProfile userProfile = new UserProfile();
        userProfile.setAccount(iAccountService.getById(id));
        userProfile.setDateCreate(new Date());
        userProfile.setIsActive(true);
        iUserProfileService.create(userProfile);

        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL), HttpStatus.OK);
    }

    @GetMapping("/workOrRest")
    public ResponseEntity<String> workOrRest(@RequestParam Long id) {
           return new ResponseEntity<>(iAccountService.workOrRest(id),HttpStatus.OK);
    }

    @GetMapping("/messageReceivers")
    ResponseEntity<List<AccountMessageDTO>> getAllMessageReceiversByAccountId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.findByUsername(userDetails.getUsername()).orElseGet(null);
        return new ResponseEntity<>(iAccountService.getAllMessageReceiversByAccountId(account.getId()), HttpStatus.OK);
    }


}

