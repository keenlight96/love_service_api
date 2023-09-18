package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public UserProfile(String lastName, String firstName, Date birthday, String country, String address, String phoneNumber, long price, String idCard, String height, String weight, String basicRequest, String facebookLink,Zone zone, List<Supply> supplies) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.idCard = idCard;
        this.height = height;
        this.weight = weight;
        this.basicRequest = basicRequest;
        this.facebookLink = facebookLink;
        this.supplies = supplies;
        this.zone = zone;
    }
}
