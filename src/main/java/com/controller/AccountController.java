package com.controller;

import com.model.Account;
import com.model.Role;
import com.model.Status;
import com.model.dto.AccountRegisterDTO;
import com.model.messageErorr.ValidStatus;
import com.service.IAccountService;
import com.service.IRoleService;
import com.service.IStatusService;
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
    IStatusService iStatusService;

    @Autowired
    IUserProfileService iUserProfileService;
    @PostMapping("/registerUser")
    ResponseEntity<AccountRegisterDTO> createAccountUser(@RequestBody AccountRegisterDTO accountDTO) {
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
        account.setNickName(accountDTO.getNickName());
        account.setAvatar("https://cdn0.iconfinder.com/data/icons/avatar-basic-colors-doodle-1/91/Avatar__Basic_Doodle_C-42-512.png");
//        accountDTO.setAvatar("https://cdn0.iconfinder.com/data/icons/avatar-basic-colors-doodle-1/91/Avatar__Basic_Doodle_C-42-512.png");
        Role role = iRoleService.findByName("ROLE_USER");
        account.setRole(role);
//        accountDTO.setRole(role);
        Status status = iStatusService.getById(3);
        account.setStatus(status);
//        accountDTO.setStatus(status);
        account.setIsActive(true);
//        accountDTO.setIsActive(true);
        iAccountService.create(account);
        return new ResponseEntity<>(new AccountRegisterDTO(ValidStatus.SUCCESSFULL), HttpStatus.OK);
    }
}

