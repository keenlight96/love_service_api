package com.controller;

import com.model.Bill;
import com.model.dto.TotalRevenueDTO;
import com.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/revenues")
public class RevenueController {
    @Autowired
    IRevenueService iRevenueService;

    @PostMapping("/totalRevenueByDay/{idAccountCCDV}/{dateStart}/{dateEnd}")
    public ResponseEntity<List<TotalRevenueDTO>> getTotalRevenueByDayForAccount(@PathVariable long idAccountCCDV,
                                                                                @PathVariable String dateStart,
                                                                                @PathVariable String dateEnd) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<TotalRevenueDTO> totalRevenueDTOList = iRevenueService.getTotalRevenueByDayForAccount(idAccountCCDV, dateFormat.parse(dateStart), dateFormat.parse(dateEnd));
            return new ResponseEntity<>(totalRevenueDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
