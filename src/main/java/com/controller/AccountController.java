package com.controller;


import com.model.dto.AccountCCDVDTO;
import com.repository.IBillRepository;
import com.model.Account;
import com.model.Role;
import com.model.Status;
import com.cofig.filter.JwtAuthenticationFilter;
import com.model.*;
import com.model.Account;
import com.model.Account;
import com.model.Role;
import com.model.Status;
import com.model.dto.AccountRegisterDTO;
import com.model.dto.AccountToken;
import com.model.messageErorr.ValidStatus;
import com.service.IAccountService;
import com.service.IRoleService;
import com.service.IStatusService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
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
    @PostMapping("/changeAvata/{id}")
    ResponseEntity<?> changeAvataInProfileByUserLogin(@PathVariable Long id,@RequestBody Account account){
        Account account1 = iAccountService.getById(id);
        account1.setAvatar(account.getAvatar());

        return new ResponseEntity<>(account1,HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    ResponseEntity<AccountRegisterDTO> createAccountUser(@RequestBody AccountRegisterDTO accountDTO) {
        if (iAccountService.findByUsername(accountDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.NAME_EXISTED), HttpStatus.OK);
        }
        if (iAccountService.findByEmail(accountDTO.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.EMAIL_EXIST), HttpStatus.OK);
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

        Status status = iStatusService.getById(3);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);

        iAccountService.create(account);
        long accountId = account.getId();

        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL,accountId), HttpStatus.OK);
    }
    @PostMapping("/registerUserAndProfile")
    ResponseEntity<AccountRegisterDTO> createAccountUserAndProfile(@RequestBody AccountRegisterDTO accountDTO) {
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
        Status status = iStatusService.getById(3);
        accountDTO.setStatus(status);
        account.setStatus(accountDTO.getStatus());
        account.setIsActive(true);
        iAccountService.create(account);
        long id = account.getId();
        UserProfile userProfile = new UserProfile();
        userProfile.setAccount(iAccountService.getById(id));
        userProfile.setDateCreate(new Date());
        iUserProfileService.create(userProfile);

        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL), HttpStatus.OK);
    }

}

