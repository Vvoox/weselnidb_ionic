package com.db.demo.rest.driver;

import com.db.demo.Enums.Role;
import com.db.demo.models.Client;
import com.db.demo.models.Driver;
import com.db.demo.models.TokenResponse;
import com.db.demo.models.User;
import com.db.demo.repositories.DriverRepository;
import com.db.demo.repositories.UserRepository;
import com.db.demo.services.AuthService;
import com.db.demo.utilities.JwtUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/driver")
@CrossOrigin(value = "*",origins = "*")
@Log4j2
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/location")
    public List<Driver> getAllDriver(){
        List<Driver> drivers = driverRepository.findAllByAvailable(true);
//        List<Driver> driversDispo = new ArrayList<>();
//        if(drivers.size()>0){
//            drivers.forEach(driver ->{
//                if(driver.isAvailable()){
//                    driversDispo.add(driver);
//                }
//            });
//        }
        log.info("Drivers dispo");
        log.info(drivers);

        return drivers;
    }

    @GetMapping("/{id}")
    public Driver getOneDriver(@PathVariable long id){
        Driver driver = driverRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Driver with id "+id+" is not found."));
        return driver;
    }
    @PostMapping("/add")
    public ResponseEntity<Driver> addDriver( @RequestBody User user){
        if(!user.getRepeatPassword().equals(user.getPassword())){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Passwords not match");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.DRIVER);
//        System.out.println(user);
        log.info(user.getLatitude());
        log.info(user.getLongitude());
        userRepository.save(user);
        Driver driver = new Driver();
        driver.setUser(user);
        driver.setAvailable(true);
        driverRepository.save(driver);
        return ResponseEntity.ok(driver);
    }

//    @PostMapping("/{id}/set-location")
//    public ResponseEntity<?> setLocation(@RequestBody double longitude , double latitude){
//        User user = getOneDriver(jwtUtils.getTokenValidity())
//    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        Driver driver = getOneDriver(id);
        driverRepository.delete(driver);
        userRepository.delete(driver.getUser());
        return ResponseEntity.ok("Driver with id "+id +" was deleted successfully.");
    }



    @PutMapping("/{id}/modify")
    public Driver modifyUser(@PathVariable long id, @RequestBody Driver driver){

        Driver oldDriver = getOneDriver(id);
        driver.setId(oldDriver.getId());
        driverRepository.save(driver);
        return driver;
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<?> authenticate (@RequestBody User user, HttpServletResponse response) throws Exception {
        System.out.println(user.getEmail() +" "+ user.getPassword());

        try {
            log.info("USER info");
            log.info(user.getEmail());
            log.info(user.getPassword());
            authService.authenticate(user.getEmail(), user.getPassword());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "L'email ou mot de passe est incorrect.");
        }

        User authenticatedUser = userRepository.findByEmail(user.getEmail());
        final String token = jwtUtils.generateToken(authenticatedUser);
        TokenResponse responseBody = new TokenResponse();
        responseBody.setToken(token);
        responseBody.setExpireAt(jwtUtils.getExpirationDateFromToken(token));
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOutDriver(){
        return ResponseEntity.ok("Logout");
    }

}
