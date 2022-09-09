package com.example.carserviceapp.service;

import com.example.carserviceapp.model.CarOwner;

import java.util.List;

public interface CarOwnerService {
    CarOwner createCarOwner(CarOwner carOwner);
    List<CarOwner> getAllCarOwners();
    CarOwner getCarOwnerById(Long carOwnerId);
    CarOwner updateCarOwnerById(Long carOwnerId);
    String deleteCarOwnerById(Long carOwnerId);

}
