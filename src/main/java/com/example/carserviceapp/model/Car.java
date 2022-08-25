package com.example.carserviceapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private String stateNumber;
    private int year;
    @OneToMany
    private List<CarOwner> owners;
}
