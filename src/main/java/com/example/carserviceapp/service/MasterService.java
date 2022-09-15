package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Master;

import java.util.List;

public interface MasterService {
    Master createMaster(Master master);
    List<Master> getAllMasters();
    Master getMasterById(Long masterId);
    Master updateMasterById(Long masterId);
    String deleteMasterById(Long masterId);
}
