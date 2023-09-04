package com.controller;

import com.model.Account;
import com.model.Supply;
import com.model.UserProfile;
import com.service.ISupplyService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getSupplyByIdAccount")
    public ResponseEntity<List<Supply>> getSupplyByIdAccount(@RequestParam long id) {
        List<Supply> list = iUserProfileService.getById(id).getSupply();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/createSupply")
    public ResponseEntity<String> createSupply(@RequestBody List<Supply> supplyList, @RequestParam int id, @RequestParam int cost, @RequestParam int hour) {
        UserProfile userProfile = iUserProfileService.getById(id);
        userProfile.setSupply(supplyList);
        userProfile.setPrice(cost);
        userProfile.setMinHour(hour);
        iUserProfileService.edit(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
