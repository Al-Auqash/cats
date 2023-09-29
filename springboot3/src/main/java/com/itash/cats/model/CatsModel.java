package com.itash.cats.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cats")
public class CatsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cat_id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "origin_id")
    private long origin_id;
    
    @Column(name = "breed_id")
    private long breed_id;

    public CatsModel() {

    }

    public CatsModel(
        long cat_id,
        String name,
        int age,
        String color,
        long origin_id,
        long breed_id
    ) {
        this.cat_id = cat_id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.origin_id = origin_id;
        this.breed_id = breed_id;
    }
    
    public long getCatId() {
        return cat_id;
    }
    
    public void setCatId(long cat_id) {
        this.cat_id = cat_id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public long getOriginId() {
        return origin_id;
    }
    
    public void setOriginId(long origin_id) {
        this.origin_id = origin_id;
    }
    
    public long getBreedId() {
        return breed_id;
    }
    
    public void setBreedId(long breed_id) {
        this.breed_id = breed_id;
    }
    
}
