package com.example.carserviceapp.dto.response;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class StuffResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean isDeleted;
}
