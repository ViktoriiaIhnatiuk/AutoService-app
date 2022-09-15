package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.CarOwnerRequestDto;
import com.example.carserviceapp.dto.response.CarOwnerResponseDto;
import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.service.CarService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CarOwnerMapper implements RequestMapper<CarOwnerRequestDto, CarOwner>,
        ResponseMapper<CarOwner, CarOwnerResponseDto> {
    private final CarMapper carMapper;
    private final CarService carService;

    public CarOwnerMapper(CarMapper carMapper,
                          CarService carService) {
        this.carMapper = carMapper;
        this.carService = carService;
    }

    @Override
    public CarOwner mapToModel(CarOwnerRequestDto carOwnerRequestDto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setName(carOwnerRequestDto.getName());
        if(carOwnerRequestDto.getCarsIds() != null) {
            carOwner.setCars(carOwnerRequestDto.getCarsIds().stream()
                    .map(carService::getCarById)
            .collect(Collectors.toList()));
        }
        return carOwner;
    }

    @Override
    public CarOwnerResponseDto mapToDto(CarOwner carOwner) {
        CarOwnerResponseDto carOwnerResponseDto = new CarOwnerResponseDto();
        carOwnerResponseDto.setId(carOwner.getId());
        carOwnerResponseDto.setName(carOwner.getName());
        if (carOwner.getCars() != null) {
            carOwnerResponseDto.setCars(carOwner.getCars().stream()
                    .map(carMapper::mapToDto)
                    .collect(Collectors.toList()));
        }
        carOwnerResponseDto.setOrders(carOwner.getOrders());
        carOwnerResponseDto.setPayedOrdersQuantity(carOwnerResponseDto.getPayedOrdersQuantity());
        carOwnerResponseDto.setDeleted(carOwner.isDeleted());
        return carOwnerResponseDto;
    }
}
