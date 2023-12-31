package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private Date date;
    @ManyToOne
    private Account accountCCDV;
    @ManyToOne
    private Account accountUser;
    @ManyToOne
    private Status status;
    private Boolean isActive;
}
