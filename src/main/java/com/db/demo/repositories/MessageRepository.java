package com.db.demo.repositories;

import com.db.demo.models.Message;
import com.db.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
