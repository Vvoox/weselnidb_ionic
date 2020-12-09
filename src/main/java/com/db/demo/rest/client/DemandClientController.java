package com.db.demo.rest.client;

import com.db.demo.models.Client;
import com.db.demo.models.Demand;
import com.db.demo.repositories.ClientRepository;
import com.db.demo.repositories.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/client/")
public class DemandClientController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private ClientController clientController;

    @PostMapping("{id}/demand")
    public ResponseEntity<Demand> createDemand(@PathVariable long id,@RequestBody Demand demand){
        clientController.getOneClient(id);
        Demand newDemand = demandRepository.save(demand);
        return ResponseEntity.ok(newDemand);
    }


    @GetMapping("{id}/demand/{idDemand}")
    public Demand getDemand(@PathVariable long id , @PathVariable long idDemand){
        Demand demand = demandRepository.findByIdAndClient_Id(idDemand,id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND , "Demand not found for Client "+idDemand));
        return demand;
    }

    @PutMapping("{id}/demand/{idDemand}/modify")
    public Demand modifydemand(@PathVariable long id , @PathVariable long idDemand , @RequestBody Demand demand){
        Demand demand1 = getDemand(id,idDemand);
        demand.setId(demand1.getId());
        demandRepository.save(demand);
        return demand;
    }

    @DeleteMapping("{id}/demand/{idDemand}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id,@PathVariable long idDemand){
        Demand demand = getDemand(id,idDemand);
        demandRepository.delete(demand);
        return ResponseEntity.ok("Demand with id "+id +" was deleted successfully.");
    }
}
