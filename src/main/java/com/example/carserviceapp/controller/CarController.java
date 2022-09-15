package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.CarRequestDto;
import com.example.carserviceapp.dto.response.CarResponseDto;
import com.example.carserviceapp.mapper.CarMapper;
import com.example.carserviceapp.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new car creating. Available for authenticated users only. " +
            "Returns a newly-created car DTO")
    public CarResponseDto createCar (@RequestBody CarRequestDto carRequestDto) {
        return carMapper.mapToDto(carService.createCar(carMapper.mapToModel(carRequestDto)));
    }

    @GetMapping
    @ApiOperation(value = "Depending on the authorities of the requesting user returns a list of " +
            "all concrete user's cars DTOs or a list of all cars DTOs if the user has ADMIN authority")
    public List<CarResponseDto> getAllCars () {
        return carService.getAllCars().stream()
                .map(carMapper:: mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarResponseDto getCarById (@PathVariable Long id) {
        return carMapper.mapToDto(carService.getCarById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }
}
