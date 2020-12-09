package com.db.demo.rest.admin;

import com.db.demo.models.User;
import com.db.demo.repositories.UserRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@Log4j2
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @GetMapping("{id}")
    public User getOneUser(@PathVariable long id){
        log.info("GETTING USER");
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id "+id+" is not found."));
        return user;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        User user = getOneUser(id);
        userRepository.delete(user);
        return ResponseEntity.ok("User with id "+id +" was deleted successfully.");
    }

    @PutMapping("{id}/modify")
    public User modifyUser(@PathVariable long id,@RequestBody User user){

        User oldUser = getOneUser(id);
        user.setId(oldUser.getId());
        userRepository.save(user);
        return user;
    }







}
