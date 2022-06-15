package com.dani.uspherejda.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SatelliteForm implements Serializable {
    private java.lang.String name;
    private java.lang.String country;
    private java.lang.String category;
    private ArrayList<java.lang.String> names;
    private List<java.lang.String> list;
    private int id;

    //Empty constructor
    public SatelliteForm(){}
    //Construtcor
    public SatelliteForm(String name, String country, String category){
        this.name = name;
        this.country = country;
        this.category = category;
        this.names = names;
    }

    //Construtcor
    public SatelliteForm(int id, String name, String country, String category){
        this.id = id;
        this.name = name;
        this.country = country;
        this.category = category;
        this.names = names;

    }

    //Getters & Setters
    public ArrayList<String> getArray(){
        return names;
    }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCategory() { return category; }
    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
    public void setCategory(String category) { this.category = category; }
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
