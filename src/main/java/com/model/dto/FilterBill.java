package com.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FilterBill {
    private String idStatus;
    private String usernameCCDV;
    private String usernameUser;

    public FilterBill(String idStatus, String usernameCCDV, String usernameUser) {
        this.idStatus = idStatus;
        this.usernameCCDV = usernameCCDV;
        this.usernameUser = usernameUser;
    }
}
