package com.example.uspherejda.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatelliteForm {
    private String name;
    private String country;
    private String category;
    private ArrayList<String> names;
    private List<String> list;

    //Empty constructor
    public SatelliteForm(){}
    //Construtcor
    public SatelliteForm(String name, String country, String category){
        this.name = name;
        this.country = country;
        this.category = category;
        this.names = names;
        addNames(name, country, category);
    }
    //Method which adds the variables to an array
    public void addNames(String name, String country, String category) {
        list = Arrays.asList(name, country, category);
        names = new ArrayList<>(list);
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
}
