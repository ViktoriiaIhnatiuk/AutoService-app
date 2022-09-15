package com.example.carserviceapp.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceResponseDto {
    private Long id;
    private String name;
    private MasterResponseDto master;
    private OrderResponseDto order;
    private BigDecimal price;
    private String status;
    private boolean isDeleted;

}
