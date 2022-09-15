package com.example.carserviceapp.dto.response;

import com.example.carserviceapp.model.Order;
import lombok.Data;
import java.util.List;

@Data
public class MasterResponseDto {
    private Long id;
    private String name;
    private List<Order> finishedOrders;
    private boolean isDeleted;
}
