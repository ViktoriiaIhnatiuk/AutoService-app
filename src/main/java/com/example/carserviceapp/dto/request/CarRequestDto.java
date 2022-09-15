package com.example.carserviceapp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private String stateNumber;
    private int year;
    private List<Long> carOwnersIds;
}
