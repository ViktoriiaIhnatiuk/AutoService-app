package com.example.carserviceapp.service.impl;

import com.example.carserviceapp.model.Status;
import com.example.carserviceapp.repository.StatusRepository;
import com.example.carserviceapp.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(
                () -> new RuntimeException("Can't find status by id " + statusId));
    }

    @Override
    public Status getStatusByName(String statusName) {
        return statusRepository.findStatusByName(statusName).orElseThrow(
                () -> new RuntimeException("Can't find status with name " + statusName));
    }

    @Override
    public Status updateStatusById(Long statusId) {
        return null;
    }

    @Override
    public String deleteStatusById(Long statusId) {
        statusRepository.deleteById(statusId);
        return "Status with id " + statusId + " has been deleted successfully";
    }
}
