package com.example.carserviceapp.controller;

import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new car creating. Available for authenticated users only. " +
            "Returns a newly-created car DTO")
    public Car createCar (@RequestBody Car car) {
        return carService.createCar(car);
    }

    @GetMapping
    @ApiOperation(value = "Depending on the authorities of the requesting user returns a list of " +
            "all concrete user's cars DTOs or a list of all cars DTOs if the user has ADMIN authority")
    public List<Car> getAllCars () {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById (@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }
}
