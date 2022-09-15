package com.example.carserviceapp.dto.response;

import com.example.carserviceapp.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class CarOwnerResponseDto {
    private Long id;

    private String name;

    private List<CarResponseDto> cars;

    private List<Order> orders;
    private Long payedOrdersQuantity;
    private boolean isDeleted;
}
