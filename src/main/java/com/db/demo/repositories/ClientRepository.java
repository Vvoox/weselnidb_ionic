package com.db.demo.repositories;

import com.db.demo.models.Client;
import com.db.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
