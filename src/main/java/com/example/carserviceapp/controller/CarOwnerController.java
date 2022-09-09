package com.example.carserviceapp.controller;

import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.service.CarOwnerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;

    public CarOwnerController(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new car owner creating. Available for authenticated users only. " +
            "Returns a newly-created car owner DTO")
    public CarOwner createCarOwner (@RequestBody CarOwner carOwner) {
        return carOwnerService.createCarOwner(carOwner);
    }

    @GetMapping
    public List<CarOwner> getAllCarOwners () {
        return carOwnerService.getAllCarOwners();
    }

    @GetMapping("/{id}")
    public CarOwner getCarOwnerById (@PathVariable Long id) {
        return carOwnerService.getCarOwnerById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable Long id) {
        return carOwnerService.deleteCarOwnerById(id);
    }
}
