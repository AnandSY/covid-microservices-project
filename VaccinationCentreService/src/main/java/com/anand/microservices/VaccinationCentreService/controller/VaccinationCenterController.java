package com.anand.microservices.VaccinationCentreService.controller;

import com.anand.microservices.VaccinationCentreService.entity.VaccinationCenter;
import com.anand.microservices.VaccinationCentreService.model.Citizen;
import com.anand.microservices.VaccinationCentreService.model.ReqRes;
import com.anand.microservices.VaccinationCentreService.repository.CenterRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

    @Autowired
    private CenterRepo centerRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/add")
    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter newCenter){
        VaccinationCenter addedCenter = centerRepo.saveAndFlush(newCenter);
        return new ResponseEntity<>(addedCenter, HttpStatus.OK);
    }


    @GetMapping(path = "/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "externalServiceCB", fallbackMethod = "handleCitizenDowntime")
    public ResponseEntity<ReqRes> getDetailsByVaccinationCenterId(@PathVariable Integer id){
        ReqRes response = new ReqRes();
        VaccinationCenter center = centerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        response.setCenter(center);

        String url = "http://CitizenService/citizen/id/"+id;

        ResponseEntity<List<Citizen>> res = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Citizen>>() {}
        );
        List<Citizen> listOfCitizen = res.getBody();
        System.out.println(listOfCitizen);
        response.setCitizens(listOfCitizen);
        System.out.println(response);
        return new ResponseEntity<ReqRes>(response,HttpStatus.OK);
    }

    public ResponseEntity<ReqRes> handleCitizenDowntime(@PathVariable Integer id, Throwable throwable) {
        ReqRes response = new ReqRes();
        VaccinationCenter center = centerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        response.setCenter(center);
        return new ResponseEntity<ReqRes>(response,HttpStatus.OK);
    }

}
