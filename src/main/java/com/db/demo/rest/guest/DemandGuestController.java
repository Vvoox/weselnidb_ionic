package com.db.demo.rest.guest;

import com.db.demo.models.Client;
import com.db.demo.models.Demand;
import com.db.demo.models.Guest;
import com.db.demo.repositories.ClientRepository;
import com.db.demo.repositories.DemandRepository;
import com.db.demo.repositories.GuestRepository;
import com.db.demo.rest.client.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/guest/")
public class DemandGuestController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    GuestController guestController;

    @GetMapping("{id}/demand/{idDemand}")
    public Demand getDemand(@PathVariable long id , @PathVariable long idDemand){
        Guest guest=guestController.getOneGuest(id);
        Demand demand = demandRepository.findByIdAndClient_Id(idDemand,guest.getId()).orElseThrow(
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
    public ResponseEntity<?> deleteUser(@PathVariable long id, @PathVariable long idDemand){
        Demand demand = getDemand(id,idDemand);
        demandRepository.delete(demand);
        return ResponseEntity.ok("Demand with id "+id +" was deleted successfully.");
    }
}
