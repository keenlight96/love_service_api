package com.controller;

import com.model.Account;
import com.model.Bill;
import com.model.dto.AccountDTO;
import com.model.dto.FilterAccountByStatusDTO;
import com.service.IAccountService;
import com.service.IBillService;
import com.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IBillService iBillService;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IUserProfileService iUserProfileService;
    // // thống kê các đơn hàng theo trạng thái và hiển  thị tất cả nếu ko thống kê
    @GetMapping("/getAllBills")
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> billList = iBillService.getAllBills();
        return new ResponseEntity<>(billList,HttpStatus.OK);
    }
    // danh sanh user theo mới nhất
    @GetMapping("/getAllUser")
    public ResponseEntity<List<Account>> getAllUserAc(){
        return new ResponseEntity<>(iAccountService.getAllUserAc(),HttpStatus.OK);

    }
    // khóa tài khoản dùng được cả cho user và ccdv
    @PostMapping("/blockAccount")
    public ResponseEntity<String> blockAccount(@RequestBody Account account){
       String str = iAccountService.blockAccount(account);
       return new ResponseEntity<>(str,HttpStatus.OK);
    }
    // danh sách ccdv theo mới nhất
    @GetMapping("/getAllCCDV")
    public ResponseEntity<List<Account>> getAllCCDVAc(){
        List<Account> accountList = iAccountService.getAllCCDVAc();
        return new ResponseEntity<>(accountList,HttpStatus.OK);
    }
    // filter lọc them status active, register, block, theo username
    @PostMapping("/getAccountUserFilter")
    public ResponseEntity<List<AccountDTO>> getAccountUserFilter(@RequestBody FilterAccountByStatusDTO filterAccountByStatusDTO){
        List<AccountDTO> accountDTOList = iAccountService.getAllAccountUserFilter(filterAccountByStatusDTO);
        return new ResponseEntity<>(accountDTOList,HttpStatus.OK);
    }
    //filter lọc them status active, register, block, theo username
    @PostMapping("/getAccountCCDVFilter")
    public ResponseEntity<List<AccountDTO>> getAccountCCDVFilter(@RequestBody FilterAccountByStatusDTO filterAccountByStatusDTO){
        List<AccountDTO> accountDTOList = iAccountService.getAllAccountCCDVFilter(filterAccountByStatusDTO);
        return new ResponseEntity<>(accountDTOList,HttpStatus.OK);
    }
    // hiển thị danh sách người ccdv chưa active
    @GetMapping("/accountCCDVRegister")
    public ResponseEntity<List<Account>> getAccountCCDVRegister(){
        List<Account> accountCCDVRegisterList = iAccountService.getAccountCCDVRegister();
        return new ResponseEntity<>(accountCCDVRegisterList,HttpStatus.OK);
    }
    // ccdv register => active
    @PostMapping("/{usernameCCDV}")
    public ResponseEntity<String> activeCCDV(@PathVariable String usernameCCDV){
        String str = iAccountService.activeCCDV(usernameCCDV);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
