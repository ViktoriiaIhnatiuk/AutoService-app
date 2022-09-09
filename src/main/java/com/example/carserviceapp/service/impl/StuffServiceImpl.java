package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Stuff;
import com.example.carserviceapp.repository.StuffRepository;
import com.example.carserviceapp.service.StuffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuffServiceImpl implements StuffService {
    private final StuffRepository stuffRepository;

    public StuffServiceImpl(StuffRepository stuffRepository) {
        this.stuffRepository = stuffRepository;
    }

    @Override
    public Stuff createStuff(Stuff stuff) {
        return stuffRepository.save(stuff);
    }

    @Override
    public List<Stuff> getAllStuff() {
        return stuffRepository.findAll();
    }

    @Override
    public Stuff getStuffById(Long stuffId) {
        return stuffRepository.findById(stuffId).orElseThrow(
                () -> new RuntimeException("Can't find stuff with id " + stuffId));
    }

    @Override
    public Stuff updateStuffById(Long stuffId) {
        return null;
    }

    @Override
    public String deleteStuffById(Long stuffId) {
       Stuff stuffToDelete = getStuffById(stuffId);
       stuffToDelete.setDeleted(true);
       stuffRepository.save(stuffToDelete);
        return "Stuff with id " + stuffId + " has been deleted successfully";
    }
}
