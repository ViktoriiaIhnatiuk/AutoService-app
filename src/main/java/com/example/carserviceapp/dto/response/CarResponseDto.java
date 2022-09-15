package com.example.carserviceapp.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private String stateNumber;
    private int year;
    private boolean isDeleted;
}
