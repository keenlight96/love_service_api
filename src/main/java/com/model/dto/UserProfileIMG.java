package com.model.dto;

import com.model.Account;
import com.model.Image;
import com.model.Interest;
import com.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class UserProfileIMG {
    UserProfile userProfile;
    List<Image> image;
    Account account;
    Interest interest;
}
