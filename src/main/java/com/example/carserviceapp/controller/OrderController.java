package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.IdRequestDto;
import com.example.carserviceapp.dto.request.OrderRequestDto;
import com.example.carserviceapp.dto.response.OrderResponseDto;
import com.example.carserviceapp.mapper.OrderMapper;
import com.example.carserviceapp.service.OrderService;
import com.example.carserviceapp.service.ServiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final ServiceService serviceService;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService,
                           ServiceService serviceService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.serviceService = serviceService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new order creating. Available for authenticated users only. " +
            "Returns a newly-created order DTO")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderMapper.mapToDto(orderService.createOrder(orderMapper.mapToModel(orderRequestDto)));
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(orderMapper:: mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return orderMapper.mapToDto(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable Long id) {
        return orderService.deleteOrderById(id);
    }

    @PutMapping("/{id}/services/add")
    public String addServiceToOrder(@PathVariable Long id, @RequestBody IdRequestDto idRequestDto) {
        orderService.addServiceToOrder(id, serviceService.getServiceById(idRequestDto.getId()));
        return "Service has been added successfully";
    }

    @PutMapping("/{id}/services/remove")
    public String removeServiceFromOrder(@PathVariable Long id, @RequestBody IdRequestDto idRequestDto) {
        orderService.removeServiceFromOrder(id, serviceService.getServiceById(idRequestDto.getId()));
        return "Service has been removed successfully";
    }
}
