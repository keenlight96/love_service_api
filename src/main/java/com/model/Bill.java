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
    private Date dateCreate;
    private Date dateStart;
    private Date dateEnd;
    private long price;
    private long total;
    private long hour;
    private String firstMessage;
    private String ccdvMessage;
    private String userMessage;
    private String adminMessage;
    @ManyToOne
    private Account accountCCDV;
    @ManyToOne
    private Account accountUser;
    @ManyToOne
    private Status status;
    private Boolean isActive;
}
