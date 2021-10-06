package com.example.uspherejda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatelliteForm {
    private String name;
    private String country;
    private String category;
    private ArrayList<String> objects;
    private List<String> list;

    //Empty constructor
    public SatelliteForm(){}
    //Construcor
    public SatelliteForm(String name, String country, String category){
        setName(name);
        setCountry(country);
        setCategory(category);
        addObject(getName(), getCountry(), getCategory());
    }
    //Method which adds the variables to an array of objects
    public void addObject(String name, String country, String category) {
        list = Arrays.asList(name, country, category);
        objects = new ArrayList<>(list);

    }
    //Getters & Setters
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCategory() { return category; }
    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
    public void setCategory(String category) { this.category = category; }
}
