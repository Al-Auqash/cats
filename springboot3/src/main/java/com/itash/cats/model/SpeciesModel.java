package com.itash.cats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "species")
public class SpeciesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "species_name")
    private String speciesName;
    
    @Column(name = "species")
    private String species;

    public SpeciesModel() {

    }

    public SpeciesModel(Long id, String species) {
        this.id = id;
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
