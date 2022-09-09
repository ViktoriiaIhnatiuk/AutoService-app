package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Service;

import java.util.List;

public interface ServiceService {
    Service createService(Service service);
    List<Service> getAllServices();
    Service getServiceById(Long favorId);
    Service updateServiceById(Long favorId);
    String deleteServiceById(Long serviceId);

}
