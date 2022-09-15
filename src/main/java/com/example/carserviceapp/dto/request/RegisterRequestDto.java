package com.example.carserviceapp.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private boolean isMaster;
}
