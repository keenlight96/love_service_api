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

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    @GetMapping("/getAllBill7DayByIDCCDV")
    ResponseEntity<List<Bill>> getAllBill7DayByIDCCDV(@RequestParam long id){
        return new ResponseEntity<>(iBillService.getBills7DayByAccountCCDV_Id(id),HttpStatus.OK);
    }
    @PostMapping("/createBill")
    ResponseEntity<String> createBill(@RequestBody Bill bill){
      if( iBillService.createBill(bill)){
          return new ResponseEntity<>("Thuê thành công",HttpStatus.OK);
      }
      return new ResponseEntity<>("Méo đủ tiền",HttpStatus.OK);
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
    @GetMapping("/getAllBilByIdUser/{id}")
    public ResponseEntity<?> getAllBillByIdUser(@PathVariable long id) {
        Optional<List<Bill>> optionalBills = iBillService.getBillByAccountUser_IdDesc(id);
        if (!optionalBills.isPresent()) {
            return new ResponseEntity<>("chưa có đơn nào", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(optionalBills, HttpStatus.OK);
        }
    }
    @PostMapping("/receivedBill/{idBill}")
    public ResponseEntity<String> receivedBill(@PathVariable long idBill) {
        return new ResponseEntity<>(iBillService.confirmBill(idBill), HttpStatus.OK);
    }

    @PostMapping("/completeBill/{idBill}")
    public ResponseEntity<String> completeBill(@PathVariable long idBill){
        return new ResponseEntity<>(iBillService.completeBill(idBill),HttpStatus.OK);
    }
    @PostMapping("/cancelBill/{idBill}/{idAccount}/{message}")
    public ResponseEntity<String> cancelBill(@PathVariable long idBill, @PathVariable long idAccount, @PathVariable String message){
        Account cancelerAccount = iAccountService.getById(idAccount);
        return new ResponseEntity<>(iBillService.cancelBill(idBill,cancelerAccount,message),HttpStatus.OK);
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
