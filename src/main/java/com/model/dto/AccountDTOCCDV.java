package com.model.dto;

import com.model.Review;
import com.model.Supply;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class AccountDTOCCDV {
    private String lastName;
    private String avatar;
    private String basicRequest;
    private List<Supply> supplies;
    private long price;
    private long rent;
    private List<Review> reviews;
}
