package com.itash.cats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_origins")
public class CatOriginsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long origin_id;

    @Column(name = "origin_name")
    private String origin_name;

    @Column(name = "description")
    private String description;

    public CatOriginsModel() {

    }

    public CatOriginsModel(
        long origin_id,
        String origin_name,
        String description
    ) {
        this.origin_id = origin_id;
        this.origin_name = origin_name;
        this.description = description;
    }

    public long getCatId() {
        return origin_id;
    }

    public void setCatId(long origin_id) {
        this.origin_id = origin_id;
    }

    public String getOriginsName() {
        return origin_name;
    }

    public void setOriginsName(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getCharacteristics() {
        return description;
    }

    public void setCharacteristics(String description) {
        this.description = description;
    }
}
