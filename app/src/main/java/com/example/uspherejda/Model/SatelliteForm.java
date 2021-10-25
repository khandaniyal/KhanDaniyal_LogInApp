package com.example.uspherejda.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatelliteForm {
    private java.lang.String name;
    private java.lang.String country;
    private java.lang.String category;
    private ArrayList<java.lang.String> names;
    private List<java.lang.String> list;

    //Empty constructor
    public SatelliteForm(){}
    //Construtcor
    public SatelliteForm(java.lang.String name, java.lang.String country, java.lang.String category){
        this.name = name;
        this.country = country;
        this.category = category;
        this.names = names;
        addNames(name, country, category);
    }
    //Method which adds the variables to an array
    public void addNames(java.lang.String name, java.lang.String country, java.lang.String category) {
        list = Arrays.asList(name, country, category);
        names = new ArrayList<>(list);
    }
    //Getters & Setters
    public ArrayList<java.lang.String> getArray(){
        return names;
    }
    public java.lang.String getName() { return name; }
    public java.lang.String getCountry() { return country; }
    public java.lang.String getCategory() { return category; }
    public void setName(java.lang.String name) { this.name = name; }
    public void setCountry(java.lang.String country) { this.country = country; }
    public void setCategory(java.lang.String category) { this.category = category; }
}
