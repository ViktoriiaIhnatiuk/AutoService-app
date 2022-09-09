package com.example.carserviceapp.repository;

import com.example.carserviceapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query(value = "SELECT * "
            + "FROM statuses "
            + "WHERE status_name = ?;", nativeQuery = true)
    Optional<Status> findStatusByName(String name);
}
