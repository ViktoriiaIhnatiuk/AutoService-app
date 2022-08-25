package com.example.carserviceapp.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "favors")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Master master;
    @ManyToOne
    private Order order;
    private BigDecimal price;
    @OneToOne
    private Status status;

}
