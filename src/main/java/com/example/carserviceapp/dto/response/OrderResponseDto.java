package com.example.carserviceapp.dto.response;

import com.example.carserviceapp.model.Service;
import com.example.carserviceapp.model.Status;
import com.example.carserviceapp.model.Stuff;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderResponseDto {
    private Long id;
    private CarOwnerResponseDto customer;
    private List<CarResponseDto> cars;
    private String description;
    private LocalDateTime orderDate;
    private List<Service> services;
    private List<Stuff> stuff;
    private BigDecimal totalPrice;
    private LocalDateTime finishDate;
    private Status status;
    private boolean isDeleted;
}
