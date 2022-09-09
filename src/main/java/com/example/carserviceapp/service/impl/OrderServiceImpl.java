package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Order;
import com.example.carserviceapp.model.Status;
import com.example.carserviceapp.model.Stuff;
import com.example.carserviceapp.repository.OrderRepository;
import com.example.carserviceapp.service.OrderService;
import com.example.carserviceapp.service.StatusService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String acceptedStatus = Status.StatusName.ACCEPTED.name();
    private static final Double serviceDiscountPercent = 0.02;
    private static final Double stuffDiscountPercent = 0.01;
    private final OrderRepository orderRepository;
    private final StatusService statusService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            StatusService statusService) {
        this.orderRepository = orderRepository;
        this.statusService = statusService;
    }

    @Override
    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(statusService.getStatusByName(acceptedStatus));
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Can't find order with id " + orderId));
    }

    @Override
    public Order updateOrderById(Long orderId) {
        return null;
    }

    @Override
    public String deleteOrderById(Long orderId) {
        Order orderToDelete = getOrderById(orderId);
        orderToDelete.setDeleted(true);
        orderRepository.save(orderToDelete);
        return "Order with id " + orderId + " has been deleted successfully";
    }

    @Override
    public Order addServiceToOrder(Long orderId, com.example.carserviceapp.model.Service service) {
        Order orderToUpdate = getOrderById(orderId);
        List<com.example.carserviceapp.model.Service> services = orderToUpdate.getServices();
        services.add(service);
        orderToUpdate.setServices(services);
        BigDecimal orderPrice = orderToUpdate.getTotalPrice();
        orderPrice.add(getServicePriceWithDiscount(
                orderToUpdate.getCustomer().getPayedOrdersQuantity(), service.getPrice()));
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public Order addStuffToOrder(Long orderId, Stuff stuff) {
        Order orderToUpdate = getOrderById(orderId);
        List<Stuff> orderStuff = orderToUpdate.getStuff();
        orderStuff.add(stuff);
        orderToUpdate.setStuff(orderStuff);
        BigDecimal orderPrice = orderToUpdate.getTotalPrice();
        orderPrice.add(getStuffPriceWithDiscount(
                orderToUpdate.getCustomer().getPayedOrdersQuantity(), stuff.getPrice()));
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public Order removeServiceFromOrder(Long orderId, com.example.carserviceapp.model.Service service) {
        Order orderToUpdate = getOrderById(orderId);
        List<com.example.carserviceapp.model.Service> services = orderToUpdate.getServices();
        services.remove(service);
        orderToUpdate.setServices(services);
        BigDecimal orderPrice = orderToUpdate.getTotalPrice();
        orderPrice.subtract(getServicePriceWithDiscount(
                orderToUpdate.getCustomer().getPayedOrdersQuantity(), service.getPrice()));
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public Order removeStuffFromOrder(Long orderId, Stuff stuff) {
        Order orderToUpdate = getOrderById(orderId);
        List<Stuff> orderStuff = orderToUpdate.getStuff();
        orderStuff.remove(stuff);
        orderToUpdate.setStuff(orderStuff);
        BigDecimal orderPrice = orderToUpdate.getTotalPrice();
        orderPrice.subtract(getStuffPriceWithDiscount(
                orderToUpdate.getCustomer().getPayedOrdersQuantity(), stuff.getPrice()));
        return orderRepository.save(orderToUpdate);
    }

    private BigDecimal getServicePriceWithDiscount(Long ordersQuantity, BigDecimal fullPrice) {
        return  fullPrice.subtract(fullPrice.multiply(BigDecimal.valueOf(ordersQuantity * serviceDiscountPercent)));
    }

    private BigDecimal getStuffPriceWithDiscount(Long ordersQuantity, BigDecimal fullPrice) {
        return  fullPrice.subtract(fullPrice.multiply(BigDecimal.valueOf(ordersQuantity * stuffDiscountPercent)));
    }
}
