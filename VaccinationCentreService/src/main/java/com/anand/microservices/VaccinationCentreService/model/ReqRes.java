package com.anand.microservices.VaccinationCentreService.model;

import com.anand.microservices.VaccinationCentreService.entity.VaccinationCenter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private VaccinationCenter center;
    private List<Citizen> citizens;

    public VaccinationCenter getCenter() {
        return center;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCenter(VaccinationCenter center) {
        this.center = center;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }

    @Override
    public String toString() {
        return "ReqRes{" +
                "center=" + center +
                ", citizens=" + citizens +
                '}';
    }
}
