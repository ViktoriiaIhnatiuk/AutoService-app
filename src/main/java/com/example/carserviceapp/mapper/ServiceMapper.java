package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.ServiceRequestDto;
import com.example.carserviceapp.dto.response.ServiceResponseDto;
import com.example.carserviceapp.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements RequestMapper<ServiceRequestDto, Service>,
    ResponseMapper<Service, ServiceResponseDto> {
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;

    public ServiceMapper(MasterMapper masterMapper,
                         OrderMapper orderMapper) {
        this.masterMapper = masterMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Service mapToModel(ServiceRequestDto serviceRequestDto) {
        Service service = new Service();
        service.setName(serviceRequestDto.getName());
        service.setPrice(serviceRequestDto.getPrice());
        return service;
    }

    @Override
    public ServiceResponseDto mapToDto(Service service) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setId(service.getId());
        serviceResponseDto.setName(service.getName());
        serviceResponseDto.setPrice(service.getPrice());
        if (service.getStatus() != null) {
            serviceResponseDto.setStatus(service.getStatus().getStatusName().name());
        }
        if (service.getMaster() != null) {
            serviceResponseDto.setMaster(masterMapper.mapToDto(service.getMaster()));
        }
        if (service.getOrder() != null) {
            serviceResponseDto.setOrder(orderMapper.mapToDto(service.getOrder()));
        }
        serviceResponseDto.setDeleted(service.isDeleted());
        return serviceResponseDto;
    }
}
