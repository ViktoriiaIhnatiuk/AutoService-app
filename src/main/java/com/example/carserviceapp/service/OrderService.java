package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.Service;
import com.example.carserviceapp.model.Stuff;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order updateOrderById(Long orderId);
    String deleteOrderById(Long orderId);
    Order addServiceToOrder(Long orderId, Service service);
    Order addStuffToOrder(Long orderId, Stuff stuff);
    Order removeServiceFromOrder(Long orderId, Service service);
    Order removeStuffFromOrder(Long orderId, Stuff stuff);
    Order approveService(Long orderId, Long serviceId);
    Order approveStuff(Long orderId, Long serviceId);
    Order rejectService(Long orderId, Long serviceId);
    Order rejectStuff(Long orderId, Long serviceId);
}
