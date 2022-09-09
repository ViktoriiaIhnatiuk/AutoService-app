package com.example.carserviceapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_owners")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany
    private List<Car> cars;
    @OneToMany
    private List<Order> orders;
    private Long payedOrdersQuantity;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getPayedOrdersQuantity() {
        return payedOrdersQuantity;
    }

    public void setPayedOrdersQuantity(Long payedOrdersQuantity) {
        this.payedOrdersQuantity = payedOrdersQuantity;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "CarOwner{" +
                "id=" + id +
                ", name=" + name +
                ", cars=" + cars +
                ", orders=" + orders +
                ", isDeleted=" + isDeleted +
                ", payedOrdersQuantity=" + payedOrdersQuantity +
                '}';
    }
}
