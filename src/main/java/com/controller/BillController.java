package com.controller;

import com.model.Account;
import com.model.Bill;
import com.model.dto.AccountCCDVDTO;
import com.service.IAccountService;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    IBillService iBillService;
    @Autowired
    IAccountService iAccountService;
    @GetMapping("/{accountccdv_id}")
    public ResponseEntity<List<Bill>> getAllByAccountCCDV_Id(@PathVariable long accountccdv_id) {
        return new ResponseEntity<>(iBillService.getAllByAccountCCDV_Id(accountccdv_id), HttpStatus.OK);
    }


 }
