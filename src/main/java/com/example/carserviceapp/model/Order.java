package com.example.carserviceapp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Car> cars;
    private String description;
    private LocalDateTime orderDate;
    @OneToMany
    private List<Favor> favors;
    @OneToMany
    private List<Stuff> stuff;
    private BigDecimal totalPrice;
    private LocalDateTime finishDate;
    @OneToOne
    private Status status;
}
