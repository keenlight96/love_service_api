package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterCCDV {
    private String nickname;
    private String zone;
    private String gender;
    private Integer year;

    public FilterCCDV(String nickname, String zone, String gender, Integer year) {
        this.nickname = nickname;
        this.zone = zone;
        this.gender = gender;
        this.year = year;
    }
}
