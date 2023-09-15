package com.model.dto;

import com.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalRevenueDTO {
    private Date startOfMonth;
    private long total;

}
