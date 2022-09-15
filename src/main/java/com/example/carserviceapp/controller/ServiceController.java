package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.ServiceRequestDto;
import com.example.carserviceapp.dto.response.ServiceResponseDto;
import com.example.carserviceapp.mapper.ServiceMapper;
import com.example.carserviceapp.service.ServiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    public ServiceController(ServiceService serviceService,
                             ServiceMapper serviceMapper) {
        this.serviceService = serviceService;
        this.serviceMapper = serviceMapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new service creating. Available for authenticated users only. " +
            "Returns a newly-created service DTO")
    public ServiceResponseDto createService(@RequestBody ServiceRequestDto serviceRequestDto) {
        return serviceMapper.mapToDto(serviceService.createService(serviceMapper.mapToModel(serviceRequestDto)));
    }

    @GetMapping
    public List<ServiceResponseDto> getAllServices() {
        return serviceService.getAllServices().stream()
                .map(serviceMapper:: mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ServiceResponseDto getServiceById(@PathVariable Long id) {
        return serviceMapper.mapToDto(serviceService.getServiceById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteServiceById(@PathVariable Long id) {
        return serviceService.deleteServiceById(id);
    }
}
