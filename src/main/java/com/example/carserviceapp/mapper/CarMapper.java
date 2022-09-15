package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.CarRequestDto;
import com.example.carserviceapp.dto.response.CarResponseDto;
import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.service.CarOwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements RequestMapper<CarRequestDto, Car>,
        ResponseMapper<Car, CarResponseDto> {
    private final CarOwnerService carOwnerService;

    public CarMapper(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @Override
    public Car mapToModel(CarRequestDto carRequestDto) {
        Car car = new Car();
        car.setManufacturer(carRequestDto.getManufacturer());
        car.setModel(carRequestDto.getModel());
        car.setYear(carRequestDto.getYear());
        car.setStateNumber(carRequestDto.getStateNumber());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(car.getId());
        carResponseDto.setManufacturer(car.getManufacturer());
        carResponseDto.setModel(car.getModel());
        carResponseDto.setYear(car.getYear());
        carResponseDto.setStateNumber(car.getStateNumber());
        carResponseDto.setDeleted(car.isDeleted());
        return carResponseDto;
    }
}
