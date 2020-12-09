package com.db.demo.repositories;

import com.db.demo.models.Driver;
import com.db.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    List<Driver> findAllByAvailable(boolean dispo);
    Optional<Driver> findByUser_Name(String name);
}
