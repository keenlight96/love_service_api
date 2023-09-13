package com.model.dto;

import com.model.Account;
import com.model.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

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
