package com.db.demo.repositories;

import com.db.demo.models.Demand;
import com.db.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemandRepository extends JpaRepository<Demand,Long> {

    Optional<Demand> findByIdAndClient_Id(long id , long idClient);
    Optional<Demand> findByIdAndDriver_Id(long id , long idDriver);
}
