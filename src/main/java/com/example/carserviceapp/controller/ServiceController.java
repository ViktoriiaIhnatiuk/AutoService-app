package com.example.carserviceapp.controller;

import com.example.carserviceapp.model.Service;
import com.example.carserviceapp.service.ServiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new service creating. Available for authenticated users only. " +
            "Returns a newly-created service DTO")
    public Service createService(@RequestBody Service service) {
        return serviceService.createService(service);
    }

    @GetMapping
    public List<Service> getAllFavors() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Service getFavorById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteFavorById(@PathVariable Long id) {
        return serviceService.deleteServiceById(id);
    }
}
