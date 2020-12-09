package com.db.demo.rest.guest;

import com.db.demo.models.Client;
import com.db.demo.models.Guest;
import com.db.demo.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/guest/")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("{id}")
    public Guest getOneGuest(@PathVariable long id){
        Guest guest = guestRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Client with id "+id+" is not found."));
        return guest;
    }
}
