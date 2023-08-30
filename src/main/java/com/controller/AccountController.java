package com.controller;

import com.model.Account;
import com.service.IAccountService;
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
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id){
        Account account = iAccountService.getById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
