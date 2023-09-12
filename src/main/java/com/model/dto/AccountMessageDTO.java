package com.model.dto;

import com.model.Message;
import com.model.Role;
import com.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountMessageDTO {
    private long id;
    private String username;
    private String nickname;
    private String avatar;
    private Role role;
    private Status status;
    private Boolean isActive;

    private Message lastMessage;

    public AccountMessageDTO(long id, String username, String nickname, String avatar, Role role, Status status, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
        this.isActive = isActive;
    }
}
