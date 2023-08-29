package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private Date dateStart;
    private Date dateEnd;
    private double total;
    private int hour;
    @ManyToOne
    private Account accountCCDV;
    @ManyToOne
    private Account accountUser;
    @ManyToOne
    private Status status;
    private Boolean isActive;
}
