package com.model.dto;

import com.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UserProfile userProfile;
    private String randomServices;
    private Double rate;
    private Long countRate;

}
