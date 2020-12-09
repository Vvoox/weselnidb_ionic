package com.db.demo.rest.driver;

import com.db.demo.models.Demand;
import com.db.demo.models.Driver;
import com.db.demo.repositories.DemandRepository;
import com.db.demo.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/driver/")
public class DemandDriverController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DemandRepository demandRepository;

    @GetMapping("{id}/demand/{idDemand}")
    public ResponseEntity<?> AcceptDemand(@PathVariable long id , @PathVariable long idDemand, @RequestBody boolean decision){
        Demand demand = demandRepository.findByIdAndDriver_Id(idDemand,id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Demand with id "+idDemand+" is not found.")
        );
        if(decision){
            demand.setDriverDecision(true);
        }else{
            demand.setDriverDecision(false);
        }
        return ResponseEntity.ok("Decesion is done with "+decision);
    }
}
