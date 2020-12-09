package com.db.demo.rest.message;

import com.db.demo.models.*;
import com.db.demo.repositories.ConversationRepository;
import com.db.demo.repositories.MessageRepository;
import com.db.demo.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@CrossOrigin()
@Log4j2
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        log.info("Sending message...");
        User sender = userRepository.findByName(message.getFromLogin()).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.NOT_FOUND , "Sender not found"));
        User receiver = userRepository.findByName(to).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.NOT_FOUND , "receiver not found"));
//        message.setDate(LocalDate.now());
        Message msg = messageRepository.save(Message
                .builder()
                .date(LocalDate.now())
                .source(sender)
                .destination(receiver)
                .build());
        Conversation conversation = conversationRepository.save(Conversation.builder()
                .msg(message.getMessage())
                .message(msg)
                .build());

        log.info("handling send message: " + message + " to: " + to );
        log.info(conversation.toString() );
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, conversation);
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greating greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greating("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
}
