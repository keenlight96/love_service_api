package com.service;

import com.model.Role;
import com.model.Status;

public interface AccountTest {
    Long getId();
    String getUsername();
    String getNickname();
    String getAvatar();
    Role getRole();
    Status getStatus();
    Boolean getIsActive();
}
