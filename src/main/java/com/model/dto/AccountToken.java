package com.model.dto;

import com.model.Role;
import com.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String username;
    private String token;
    private String nickName;
    private String avatar;
    private long balance;

    private Role role;
    private Status status;
    private Boolean isActive;
}
