package com.example.projetandroid.model;

import android.util.JsonReader;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class City {

    private int id;
    private String name;
    private int postCode;
    private int population;
    private int numDepartment;
    private String nameDepartment;
    private int numRegion;
    private String nameRegion;

    public City(int id, String name, int postCode, int population, int numDepartment, String nameDepartment, int numRegion, String nameRegion) {
        this.id = id;
        this.name = name;
        this.postCode = postCode;
        this.population = population;
        this.numDepartment = numDepartment;
        this.nameDepartment = nameDepartment;
        this.numRegion = numRegion;
        this.nameRegion = nameRegion;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getNumDepartment() {
        return numDepartment;
    }

    public void setNumDepartment(int numDepartment) {
        this.numDepartment = numDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public int getNumRegion() { return numRegion; }

    public void setNumRegion(int numRegion) { this.numRegion = numRegion; }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}
