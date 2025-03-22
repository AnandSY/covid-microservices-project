package com.anand.microservices.CitizenService.repository;

import com.anand.microservices.CitizenService.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen,Integer> {
    public List<Citizen> findByVaccinationCentreId(Integer id);
}
