package com.example.carserviceapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "masters")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Order> finishedOrders;
    @OneToMany
    private List<Order> currentOrders;
    private Long userId;
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

    public List<Order> getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(List<Order> finishedOrders) {
        this.finishedOrders = finishedOrders;
    }

    public List<Order> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(List<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", finishedOrders=" + finishedOrders +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
