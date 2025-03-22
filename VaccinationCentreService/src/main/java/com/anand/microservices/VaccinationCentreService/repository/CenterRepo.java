package com.anand.microservices.VaccinationCentreService.repository;

import com.anand.microservices.VaccinationCentreService.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepo extends JpaRepository<VaccinationCenter,Integer> {

}
