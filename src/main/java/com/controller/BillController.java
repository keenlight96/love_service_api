package com.controller;

import com.model.Bill;
import com.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/getAllBilByIdCCdv/{id}")
    public ResponseEntity<?> getAllBillByIdCCDV(@PathVariable long id) {
        Optional<List<Bill>> optionalBills = iBillService.findAllByAccountCCDV_IOrderByIdDesc(id);
        if (!optionalBills.isPresent()) {
            return new ResponseEntity<>("chưa có đơn nào", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(optionalBills, HttpStatus.OK);
        }
    }
    @PostMapping("/{idBill}")
    public ResponseEntity<String> receivedBill(@PathVariable long idBill) {
        return new ResponseEntity<>(iBillService.confirmBill(idBill), HttpStatus.OK);
    }
    @GetMapping("/getAllBillIdByAccount/{id}")
    public ResponseEntity<?> getAllBillByIdAccount(@PathVariable long id) {
        Optional<List<Bill>> optionalBills = iBillService.getAllByAccountCCDV_IdOrAccountUser_Id(id);
        if (!optionalBills.isPresent()) {
            return new ResponseEntity<>("chưa có đơn nào", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(optionalBills, HttpStatus.OK);
        }
    }
}
