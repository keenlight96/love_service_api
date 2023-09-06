package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileFilterDTO {
    private long id;
    private String firstName;
    private String lastName;
    private int birthday;
    private String gender;
    private String address;
    private  long views;
    private int total_bill;
}
