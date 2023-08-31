package com.model.dto;

import com.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class UserProfileIMG {
    UserProfile userProfile;
    List<Image> image;
    Account account;
    List<Interest> interests;
    List<Bill> bills;
}
