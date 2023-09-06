package com.controller;

import com.model.Supply;
import com.model.UserProfile;
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


    @GetMapping("/getAllSupply")
    public ResponseEntity<List<Supply>> getAllSupply() {
        List<Supply> list = iSupplyService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getSupplyByUserID")
    ResponseEntity<UserProfile> getPriceAndMinHourByID(@RequestParam long id){
        UserProfile userProfile = iUserProfileService.getByAccountId(id);
        return new ResponseEntity<>(userProfile,HttpStatus.OK);
    }

    @PostMapping("/createSupply")
    public ResponseEntity<String> createSupply(@RequestBody List<Supply> supplyList, @RequestParam int id, @RequestParam int cost) {
        UserProfile userProfile = iUserProfileService.getById(id);
        userProfile.setSupplies(supplyList);
        userProfile.setPrice(cost);
        iUserProfileService.edit(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Supply>> getAll(){
        return new ResponseEntity<>(iSupplyService.getAll(), HttpStatus.OK);
    }
}
