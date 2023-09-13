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

    @GetMapping("/getAllBill7DayByIDCCDV")
    ResponseEntity<List<Bill>> getAllBill7DayByIDCCDV(@RequestParam long id){
        return new ResponseEntity<>(iBillService.getBills7DayByAccountCCDV_Id(id),HttpStatus.OK);
    }
    @PostMapping("/createBill")
    ResponseEntity<String> createBill(@RequestBody Bill bill){
      if( iBillService.createBill(bill)){
          return new ResponseEntity<>(HttpStatus.OK);
      }
      return new ResponseEntity<>("méo đủ tiền",HttpStatus.OK);
    }

}
