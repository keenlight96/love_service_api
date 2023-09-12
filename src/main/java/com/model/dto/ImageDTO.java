package com.model.dto;

import com.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {
    private long id;
    private List<String> img;
    private Account account;
    private Boolean isActive;
}
