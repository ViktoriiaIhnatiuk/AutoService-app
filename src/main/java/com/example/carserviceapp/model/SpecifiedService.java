package com.example.carserviceapp.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "specified_services")
public class SpecifiedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Master master;
    @ManyToOne
    private Order order;
    private BigDecimal price;
    @OneToOne
    private Status status;
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

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name=" + name +
                ", master=" + master +
                ", order=" + order +
                ", price=" + price +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
