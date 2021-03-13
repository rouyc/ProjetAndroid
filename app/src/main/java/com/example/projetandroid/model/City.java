package com.example.projetandroid.model;

public class City {

    private String id;
    private String name;
    private int postCode;
    private String population;
    private String numDepartment;
    private String nameDepartment;
    private String numRegion;
    private String nameRegion;

    public City(String id, String name, int postCode, String population, String numDepartment, String nameDepartment, String numRegion, String nameRegion) {
        this.id = id;
        this.name = name;
        this.postCode = postCode;
        this.population = population;
        this.numDepartment = numDepartment;
        this.nameDepartment = nameDepartment;
        this.numRegion = numRegion;
        this.nameRegion = nameRegion;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getNumDepartment() {
        return numDepartment;
    }

    public void setNumDepartment(String numDepartment) {
        this.numDepartment = numDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getNumRegion() { return numRegion; }

    public void setNumRegion(String numRegion) { this.numRegion = numRegion; }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}
