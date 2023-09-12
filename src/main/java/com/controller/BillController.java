package com.controller;

import com.model.Bill;
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
    @GetMapping("/{accountccdv_id}")
    public ResponseEntity<List<Bill>> getAllByAccountCCDV_Id(@PathVariable long accountccdv_id) {
        return new ResponseEntity<>(iBillService.getAllByAccountCCDV_Id(accountccdv_id), HttpStatus.OK);
    }
    @GetMapping("/listBillByAccountUser/{idAccountUser}")
    public ResponseEntity<List<Bill>> getAllBillByAccountUser(@PathVariable long idAccountUser){
        return new ResponseEntity<>(iBillService.getAllBillByAccountUser(idAccountUser),HttpStatus.OK);
    }
    @GetMapping("/findBill/{idBill}")
    public ResponseEntity<Bill> getBillByIdBill(@PathVariable long idBill){
        Bill bill = iBillService.getById(idBill);
        return new ResponseEntity<>(bill,HttpStatus.OK);
    }
}
