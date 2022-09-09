package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.repository.MasterRepository;
import com.example.carserviceapp.service.MasterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public Master createMaster(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    @Override
    public Master getMasterById(Long masterId) {
        return masterRepository.findById(masterId).orElseThrow(
                () -> new RuntimeException("Can't find master by id " + masterId));
    }

    @Override
    public Master updateMasterById(Long masterId) {
        return null;
    }

    @Override
    public String deleteMasterById(Long masterId) {
        Master masterToDelete = getMasterById(masterId);
        masterToDelete.setDeleted(true);
        masterRepository.save(masterToDelete);
        return "Can't delete master with id " + masterId + " has been deleted successfully";
    }
}
