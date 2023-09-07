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
    private String weight;
    private String describes;
    private String basicRequest;
    private String facebookLink;
    private Boolean isVIP;
    private Boolean isActive;
    @OneToOne
    private Account account;
    @ManyToOne
    private Zone zone;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Supply> supplies;
    @ManyToMany
    private List<Interest> interests;

}
