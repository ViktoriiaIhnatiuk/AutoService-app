package com.example.carserviceapp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long carOwnerId;
    private List<Long> carsIds;
    private String description;
}
