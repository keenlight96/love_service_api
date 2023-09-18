package com.model.dto;

import com.model.Role;
import com.model.Status;
import com.model.messageErorr.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterDTO {
    private long id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String nickName;
    private Role role;
    private Status status;
    private Boolean isActive;
    private ValidStatus validStatus;


    public AccountRegisterDTO(ValidStatus status, long accountId) {
        this.validStatus = status;
        this.id = accountId;
    }


    public AccountRegisterDTO(ValidStatus validStatus) {
        this.validStatus =validStatus;
    }
}

