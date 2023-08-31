package com.controller;

import com.model.dto.AccountDTOCCDV;
import com.repository.IBillRepository;
import com.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IBillRepository iBillRepository;
    @GetMapping("")
    public ResponseEntity<List<AccountDTOCCDV>> getAccountCCDVNamAndNu() {
        List<AccountDTOCCDV> top4MaleAccountCCDV = iBillRepository.findTop4MaleAccountCCDV();
        List<AccountDTOCCDV> top8FemaleAccountCCDV = iBillRepository.findTop8FemaleAccountCCDV();
        List<AccountDTOCCDV> top12AccountCCDV = new ArrayList<>();
        top12AccountCCDV.addAll(top4MaleAccountCCDV);
        top12AccountCCDV.addAll(top8FemaleAccountCCDV);
        return ResponseEntity.ok(top12AccountCCDV);
    }
}
