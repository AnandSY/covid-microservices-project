package com.anand.microservices.CitizenService.controller;

import com.anand.microservices.CitizenService.entity.Citizen;
import com.anand.microservices.CitizenService.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<List<Citizen>> findByVaccinationCentreId(@PathVariable Integer id){
        List<Citizen> listCitizen = citizenService.findByVaccinationCentreId(id);
        return new ResponseEntity<List<Citizen>>(listCitizen, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen){
        System.out.println("Received Request: " + newCitizen); // Print full object
        Citizen savedCitizen = citizenService.addCitizen(newCitizen);
        return new ResponseEntity<>(savedCitizen,HttpStatus.OK);
    }
}
