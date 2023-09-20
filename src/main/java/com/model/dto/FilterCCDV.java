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
    private Long minPrice;
    private Long maxPrice;

    public FilterCCDV(String zone, String gender, Integer year, Long minPrice, Long maxPrice) {
        this.zone = zone;
        this.gender = gender;
        this.year = year;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
