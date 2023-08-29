package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String country;
    private String address;
    private double balance;
    private String phoneNumber;
    private String idCard;
    private String gender;
    private String height;
    private String width;
    private String basicRequest;
    private String facebookLink;
    private Boolean isVIP;
    private Boolean isActive;
    @OneToOne
    private Account account;
    @ManyToOne
    private Zone zone;
}
