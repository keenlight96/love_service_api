package com.controller;

import com.model.Account;
import com.model.Report;
import com.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    IReportService iReportService;

    @PostMapping("/getAccountReceiverReport")
    public ResponseEntity<List<Report>> getAccountReceiverReport(@RequestParam String usernameParam) {
        return new ResponseEntity<>(iReportService.getAccountReceiverReport(usernameParam), HttpStatus.OK);
    }

    @PostMapping("/sendReport")
    public ResponseEntity<String> sendReport(@RequestBody Report report) {
        return new ResponseEntity<>(iReportService.sendReport(report), HttpStatus.OK);
    }

}
