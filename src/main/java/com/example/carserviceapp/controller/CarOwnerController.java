package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.CarOwnerRequestDto;
import com.example.carserviceapp.dto.response.CarOwnerResponseDto;
import com.example.carserviceapp.mapper.CarOwnerMapper;
import com.example.carserviceapp.service.CarOwnerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;

    public CarOwnerController(CarOwnerService carOwnerService,
                              CarOwnerMapper carOwnerMapper) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new car owner creating. Available for authenticated users only. " +
            "Returns a newly-created car owner DTO")
    public CarOwnerResponseDto createCarOwner (@RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        return carOwnerMapper.mapToDto(carOwnerService.createCarOwner(
                carOwnerMapper.mapToModel(carOwnerRequestDto)));
    }

    @GetMapping
    public List<CarOwnerResponseDto> getAllCarOwners () {
        return carOwnerService.getAllCarOwners().stream()
                .map(carOwnerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarOwnerResponseDto getCarOwnerById (@PathVariable Long id) {
        return carOwnerMapper.mapToDto(carOwnerService.getCarOwnerById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable Long id) {
        return carOwnerService.deleteCarOwnerById(id);
    }
}
