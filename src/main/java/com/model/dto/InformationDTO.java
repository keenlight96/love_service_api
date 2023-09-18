package com.model.dto;

import com.model.Supply;
import com.model.Zone;
import com.model.messageErorr.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationDTO {
    private long id;

    private String avatar;
    private String email;
    private String nickname;
    private String lastName;
    private String firstName;
    private String birthday;
    private String country;
    private String address;
    private String phoneNumber;
    private String gender;
    private String height;
    private String weight;
    private String describes;
    private String basicRequest;
    private String facebookLink;
    private List<Supply> supplies;
    private Zone zone;
    private ValidStatus validStatus;

    public InformationDTO(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }
}
