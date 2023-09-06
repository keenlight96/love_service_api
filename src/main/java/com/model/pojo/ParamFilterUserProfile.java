package com.model.pojo;

import com.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamFilterUserProfile {
    private String firstName;
    private String lastName;
    private int birthday;
    private String gender;
    private String address;
    private  long views;
    private  String order;
}
