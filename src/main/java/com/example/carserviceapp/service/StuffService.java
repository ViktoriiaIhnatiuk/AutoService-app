package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Stuff;

import java.util.List;

public interface StuffService {
    Stuff createStuff(Stuff stuff);
    List<Stuff> getAllStuff();
    Stuff getStuffById(Long stuffId);
    Stuff updateStuffById(Long stuffId);
    String deleteStuffById(Long stuffId);

}
