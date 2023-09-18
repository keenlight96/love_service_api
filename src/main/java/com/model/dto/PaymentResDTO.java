package com.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentResDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}
