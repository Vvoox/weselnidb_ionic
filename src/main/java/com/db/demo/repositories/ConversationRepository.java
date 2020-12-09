package com.db.demo.repositories;

import com.db.demo.models.Conversation;
import com.db.demo.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
