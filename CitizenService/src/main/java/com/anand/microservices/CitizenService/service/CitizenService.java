package com.anand.microservices.CitizenService.service;

import com.anand.microservices.CitizenService.entity.Citizen;
import com.anand.microservices.CitizenService.repository.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepo citizenRepo;

    public List<Citizen> findByVaccinationCentreId(Integer id){
        List<Citizen> citizens = citizenRepo.findByVaccinationCentreId(id);
        return citizens;
    }

    public Citizen addCitizen(Citizen newCitizen) {
        Citizen savedCitizen = citizenRepo.save(newCitizen);
        return savedCitizen;
    }
}
