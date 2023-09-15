package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterCCDV {
    private String firstName;
    private String lastName;
    private String zone;
    private String gender;
    private Integer year;

    public FilterCCDV(String firstName, String lastName, String zone, String gender, Integer year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zone = zone;
        this.gender = gender;
        this.year = year;
    }
}
