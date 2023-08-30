package com.controller;

import com.model.Account;
import com.model.Role;
import com.model.UserProfile;
import com.service.IAccountService;
import com.service.IRoleService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IUserProfileService iUserProfileService;
@PostMapping("/registerUser")
    ResponseEntity<Account> createAccountUser(@RequestBody Account account) {
        account.setAvatar("https://cdn0.iconfinder.com/data/icons/avatar-basic-colors-doodle-1/91/Avatar__Basic_Doodle_C-42-512.png");
        Role role = iRoleService.getById(2);
        account.setRole(role);
        iAccountService.create(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}

