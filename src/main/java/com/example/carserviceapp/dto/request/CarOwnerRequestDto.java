package com.example.carserviceapp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CarOwnerRequestDto {
    private String name;
    private List<Long> carsIds;
}
