package com.model.dto;

import com.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BillMessageDTO {
    Bill bill;
    String message;
}
