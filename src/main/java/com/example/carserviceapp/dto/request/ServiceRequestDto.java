package com.example.carserviceapp.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceRequestDto {
    private String name;
    private BigDecimal price;
}
