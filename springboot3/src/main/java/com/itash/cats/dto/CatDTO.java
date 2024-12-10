package com.itash.cats.dto;

public class CatDTO {
    private Long catId;
    private String name;
    private int age;
    private String color;
    private Long originId;
    private Long breedId;
    private String originName;
    private String breedName;

    public CatDTO() {
    }

    public CatDTO(Long catId, String name, int age, String color, Long originId, Long breedId, String originName, String breedName) {
        this.catId = catId;
        this.name = name;
        this.age = age;
        this.color = color;
        this.originId = originId;
        this.breedId = breedId;
        this.originName = originName;
        this.breedName = breedName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
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

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
