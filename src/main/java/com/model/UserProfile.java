package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateCreate;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String country;
    private String address;
    private long balance;
    private String phoneNumber;
    private long price;
    private long views;
    private String idCard;
    private String gender;
    private String height;
    private String weigh;
    private String describe;
    private String basicRequest;
    private String facebookLink;
    private Boolean isVIP;
    private Boolean isActive;
    @OneToOne
    private Account account;
    @ManyToOne
    private Zone zone;
    @ManyToMany
    private List<Supply> supply;
}
