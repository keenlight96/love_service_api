package com.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Account accountCCDV;
    @ManyToOne
    private Account accountUser;
    private int rating;
    @Lob
    private String content;
    private Boolean isActive;
}
