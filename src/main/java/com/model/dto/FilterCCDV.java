package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCCDV {
    private String firstName;
    private String lastName;
    private String zone;
    private String gender;
    private Integer year;

}
