package com.model.dto;

import com.model.messageErorr.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationDTO {
    private long id;
    private String username;
    private String avatar;
    private String email;
    private String nickname;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String country;
    private String address;
    private String phoneNumber;
    private String gender;
    private String height;
    private String weight;
    private String describes;
    private String basicRequest;
    private String facebookLink;
    private ValidStatus validStatus;

}
