package com.model.dto;

import com.model.Account;
import com.model.Review;
import com.model.Supply;
import com.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
public class AccountCCDVDTO {
    private UserProfile userProfile;
    private Account account;
    private String randomServices;
    private Double rate;
    private Long countRate;
    private long countRent;

    public AccountCCDVDTO(UserProfile userProfile, Account account, String randomServices, Double rate, Long countRate, long countRent) {
        this.userProfile = userProfile;
        this.account = account;
        this.randomServices = randomServices;
        this.rate = rate;
        this.countRate = countRate;
        this.countRent = countRent;
    }
}
