package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.OrderRequestDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.service.CarOwnerService;
import com.example.carserviceapp.service.CarService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper implements RequestMapper<OrderRequestDto, Order>,
    ResponseMapper<Order, OrderResponseDto> {
    private final CarOwnerMapper carOwnerMapper;
    private final CarMapper carMapper;
    private final CarOwnerService carOwnerService;
    private final CarService carService;

    public OrderMapper(CarOwnerMapper carOwnerMapper,
                       CarMapper carMapper,
                       CarOwnerService carOwnerService,
                       CarService carService) {
        this.carOwnerMapper = carOwnerMapper;
        this.carMapper = carMapper;
        this.carOwnerService = carOwnerService;
        this.carService = carService;
    }

    @Override
    public Order mapToModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        if (orderRequestDto.getCarOwnerId() != null) {
            order.setCustomer(carOwnerService.getCarOwnerById(orderRequestDto.getCarOwnerId()));
        }
        if (orderRequestDto.getCarsIds() != null) {
            order.setCars(orderRequestDto.getCarsIds().stream()
                    .map(carService:: getCarById)
                    .collect(Collectors.toList()));
        }
        order.setDescription(orderRequestDto.getDescription());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setCustomer(carOwnerMapper.mapToDto(order.getCustomer()));
        orderResponseDto.setCars(order.getCars().stream()
                .map(carMapper:: mapToDto)
                .collect(Collectors.toList()));
        orderResponseDto.setDescription(order.getDescription());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setServices(order.getServices());
        orderResponseDto.setStuff(order.getStuff());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setFinishDate(order.getFinishDate());
        orderResponseDto.setStatus(order.getStatus());
        orderResponseDto.setDeleted(order.isDeleted());
        return orderResponseDto;
    }
}
