package com.model.dto;

import com.model.Account;
import com.model.Bill;
import com.model.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {
    private Account account;
    private UserProfile userProfile;

    public AccountDTO(Account account, UserProfile userProfile) {
        this.account = account;
        this.userProfile = userProfile;
    }
}
