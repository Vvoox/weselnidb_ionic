package com.db.demo.repositories;

import com.db.demo.models.Guest;
import com.db.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
