package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.CarOwner;
import com.example.carserviceapp.repository.CarOwnerRepository;
import com.example.carserviceapp.service.CarOwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;

    public CarOwnerServiceImpl(CarOwnerRepository carOwnerRepository) {
        this.carOwnerRepository = carOwnerRepository;
    }

    @Override
    public CarOwner createCarOwner(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    @Override
    public List<CarOwner> getAllCarOwners() {
        return carOwnerRepository.findAll();
    }

    @Override
    public CarOwner getCarOwnerById(Long carOwnerId) {
        return carOwnerRepository.findById(carOwnerId).orElseThrow(
                () -> new RuntimeException("Can't find carOwner with id " + carOwnerId));
    }

    @Override
    public CarOwner updateCarOwnerById(Long carOwnerId) {
        return null;
    }

    @Override
    public String deleteCarOwnerById(Long carOwnerId) {
        CarOwner carOwnerToDelete = getCarOwnerById(carOwnerId);
        carOwnerToDelete.setDeleted(true);
        carOwnerRepository.save(carOwnerToDelete);
        return "CarOwner with id " + carOwnerId + " has been deleted successfully";
    }
}
