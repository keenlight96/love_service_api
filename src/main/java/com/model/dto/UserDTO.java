package com.model.dto;

import com.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private UserProfile userProfile;
    private String randomServices;
    private Double rate;
    private Long countRate;

    public UserDTO(UserProfile userProfile, String randomServices, Double rate, Long countRate) {
        this.userProfile = userProfile;
        this.randomServices = randomServices;
        this.rate = rate;
        this.countRate = countRate;
    }
}
