package com.anand.microservices.VaccinationCentreService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVaccinationCentreId() {
        return vaccinationCentreId;
    }

    public void setVaccinationCentreId(int vaccinationCentreId) {
        this.vaccinationCentreId = vaccinationCentreId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int vaccinationCentreId;

    @Override
    public String toString() {
        return "Citizen{name='" + name + "', vaccinationCentreId=" + vaccinationCentreId + "}";
    }

}
