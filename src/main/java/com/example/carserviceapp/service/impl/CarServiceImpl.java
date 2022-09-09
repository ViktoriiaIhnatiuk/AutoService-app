package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Car;
import com.example.carserviceapp.repository.CarRepository;
import com.example.carserviceapp.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow(
                () -> new RuntimeException("Can't find car by id " + carId)
        );
    }

    @Override
    public Car updateCarById(Long carId) {
        return null;
    }

    @Override
    public String deleteCarById(Long carId) {
        Car carToDelete = getCarById(carId);
        carToDelete.setDeleted(true);
        carRepository.save(carToDelete);
        return "Car with id " + carId + " has been deleted successfully";
    }
}
