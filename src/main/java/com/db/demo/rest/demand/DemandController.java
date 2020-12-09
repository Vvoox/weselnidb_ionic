package com.db.demo.rest.demand;

import com.db.demo.models.Client;
import com.db.demo.models.Demand;
import com.db.demo.repositories.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/demand/")
public class DemandController {

    @Autowired
    private DemandRepository demandRepository;

    @GetMapping()
    public List<Demand> getAllDemands(){
        List<Demand> demandList = demandRepository.findAll();
        return demandList;
    }

    @GetMapping("{id}")
    public Demand getOneDemand(@PathVariable long id){
        Demand demand = demandRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Demand with id "+id+" not found.")
        );
        return demand;
    }

    @PutMapping("{id}/modify")
    public Demand modifyDemand(@PathVariable long id , @RequestBody Demand demand){
        Demand oldDemand = getOneDemand(id);
        demand.setId(oldDemand.getId());
        demandRepository.save(demand);
        return demand;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        Demand demand = getOneDemand(id);
        demandRepository.delete(demand);
        return ResponseEntity.ok("Demand with id "+id +" was deleted successfully.");
    }
}
