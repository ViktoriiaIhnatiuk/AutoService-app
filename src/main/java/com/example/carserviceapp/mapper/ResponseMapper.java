package com.example.carserviceapp.mapper;

public interface ResponseMapper <M, D>{
    D mapToDto(M model);
}
