package com.itash.cats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_breeds")
public class CatBreedsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long breed_id;

    @Column(name = "breed_name")
    private String breed_name;
    
    @Column(name = "characteristics")
    private String characteristics;

    public CatBreedsModel() {

    }

    public CatBreedsModel(
        long breed_id,
        String breed_name,
        String characteristics
    ) {
        this.breed_id = breed_id;
        this.breed_name = breed_name;
        this.characteristics = characteristics;
    }
    
    public long getCatId() {
        return breed_id;
    }
    
    public void setCatId(long breed_id) {
        this.breed_id = breed_id;
    }
    
    public String getBreedName() {
        return breed_name;
    }
    
    public void setBreedName(String breed_name) {
        this.breed_name = breed_name;
    }
    
    public String getCharacteristics() {
        return characteristics;
    }
    
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
