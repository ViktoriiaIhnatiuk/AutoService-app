package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Service;
import com.example.carserviceapp.model.Status;
import com.example.carserviceapp.repository.ServiceRepository;
import com.example.carserviceapp.service.ServiceService;
import com.example.carserviceapp.service.StatusService;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private static final String toDoStatus = Status.StatusName.TO_DO.name();
    private final ServiceRepository serviceRepository;
    private final StatusService statusService;

    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              StatusService statusService) {
        this.serviceRepository = serviceRepository;
        this.statusService = statusService;
    }

    @Override
    public Service createService(Service service) {
        service.setStatus(statusService.getStatusByName(toDoStatus));
        return serviceRepository.save(service);
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(
                () -> new RuntimeException("Can't find service with id " + serviceId));
    }

    @Override
    public Service updateServiceById(Long serviceId) {
        return null;
    }

    @Override
    public String deleteServiceById(Long serviceId) {
        Service serviceToDelete = getServiceById(serviceId);
        serviceToDelete.setDeleted(true);
        serviceRepository.save(serviceToDelete);
        return "Service with id " + serviceId + " has been deleted successfully";
    }
}
