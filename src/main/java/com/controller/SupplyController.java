package com.controller;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.service.IAccountService;
import com.service.ISupplyService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/supplies")
public class SupplyController {
    @Autowired
    ISupplyService iSupplyService;
    @Autowired
    IUserProfileService iUserProfileService;
    @Autowired
    IAccountService iAccountService;


    @GetMapping("/getAllSupply")
    public ResponseEntity<List<Supply>> getAllSupply() {
        List<Supply> list = iSupplyService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getSupplyByAccountID/{id}")
    ResponseEntity<?> getSupplyByAccountId(@PathVariable Long id) {
        Account account = iAccountService.getById(id);
        UserProfile userProfile = iUserProfileService.getByAccountId(account.getId());
        List<Supply> supply = (List<Supply>) iSupplyService.getById(userProfile.getId());
        return new ResponseEntity<>(supply, HttpStatus.OK);
    }

    @GetMapping("/getSupplyByUserID")
    ResponseEntity<UserProfile> getPriceAndMinHourByID(@RequestParam long id) {
        UserProfile userProfile = iUserProfileService.getByAccountId(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping("/createSupply")
    public ResponseEntity<String> createSupply(@RequestBody List<Supply> supplyList, @RequestParam int id, @RequestParam int cost) {
        UserProfile userProfile = iUserProfileService.getById(id);
        userProfile.setSupplies(supplyList);
        userProfile.setPrice(cost);
        iUserProfileService.edit(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/getAllActive")
    public ResponseEntity<List<Supply>> getAllActive() {
        return new ResponseEntity<>(iSupplyService.getAllActive(), HttpStatus.OK);
    }

    @GetMapping("getSupplyList")
    ResponseEntity<List<Supply>> getSupplyList() {
        List<Supply> supplyList = iSupplyService.getAll();
        return new ResponseEntity<>(supplyList, HttpStatus.OK);
    }
}
