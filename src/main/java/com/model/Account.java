package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;

    private String password;
    private String avatar;
    private String email;
    private String nickName;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Status status;
    private Boolean isActive;
}
