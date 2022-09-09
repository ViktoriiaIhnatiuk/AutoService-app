package com.example.carserviceapp.service;

import com.example.carserviceapp.model.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(Status status);
    List<Status> getAllStatuses();
    Status getStatusById(Long statusId);
    Status getStatusByName(String statusName);
    Status updateStatusById(Long statusId);
    String deleteStatusById(Long statusId);

}
