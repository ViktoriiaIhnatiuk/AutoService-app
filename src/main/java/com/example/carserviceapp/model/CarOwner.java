package com.example.carserviceapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_owners")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Car> cars;
    @OneToMany
    private List<Order> orders;
}
