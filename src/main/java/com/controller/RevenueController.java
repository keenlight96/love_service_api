package com.controller;

import com.model.dto.DateDTO;
import com.model.dto.TotalRevenueDTO;
import com.service.IRevenueService;
import com.service.ipml.RevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/revenues")
public class RevenueController {
    @Autowired
    IRevenueService iRevenueService;
    @Autowired
    RevenueServiceImpl revenueService;

    //    @GetMapping("/totalRevenueByDay/{idAccountCCDV}")
//    public ResponseEntity<Long> getTotalRevenueByDayForAccount(@PathVariable long idAccountCCDV) {
//        return new ResponseEntity<>(iRevenueService.getTotalRevenueByDayAccount(idAccountCCDV), HttpStatus.OK);
//    }
//    @PostMapping("/totalRevenueByDay/{idAccountCCDV}")
//    public ResponseEntity<List<TotalRevenueDTO>> getTotalRevenueByDayForAccount(@PathVariable long idAccountCCDV, @RequestBody DateDTO dateDTO){
//        Date dateStart = dateDTO.getStartOfMonth();
//        Date dateEnd = dateDTO.getStartOfMonth();
//        try {
//            List<TotalRevenueDTO> totalRevenueDTOList = iRevenueService.getTotalRevenueByDayForAccount(idAccountCCDV, dateStart,dateEnd);
//            return new ResponseEntity<>(totalRevenueDTOList, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
