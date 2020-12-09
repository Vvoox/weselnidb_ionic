package com.db.demo.rest.client;

import com.db.demo.models.Client;
import com.db.demo.models.User;
import com.db.demo.repositories.ClientRepository;
import com.db.demo.repositories.DemandRepository;
import com.db.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/client/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DemandRepository demandRepository;

    @GetMapping("{id}")
    public Client getOneClient(@PathVariable long id){
        Client client = clientRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Client with id "+id+" is not found."));
        return client;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        Client client = getOneClient(id);
        clientRepository.delete(client);
        userRepository.delete(client.getUser());
        return ResponseEntity.ok("Client with id "+id +" was deleted successfully.");
    }


    @PutMapping("{id}/modify")
    public Client modifyUser(@PathVariable long id,@RequestBody Client client){
        Client oldClient = getOneClient(id);
        client.setId(oldClient.getId());
        clientRepository.save(client);
        return client;
    }

}
