package com.model;

import javax.persistence.*;

@Entity
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameSupply;
    private int type;
    private Boolean isActive;
}
