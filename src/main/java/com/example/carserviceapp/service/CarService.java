package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Car;

import java.util.List;

public interface CarService {
    Car createCar(Car car);
    List<Car> getAllCars();
    Car getCarById(Long carId);
    Car updateCarById(Long carId);
    String deleteCarById(Long carId);

}
