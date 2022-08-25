package com.example.carserviceapp.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name= "stuff")
public class Stuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
}
