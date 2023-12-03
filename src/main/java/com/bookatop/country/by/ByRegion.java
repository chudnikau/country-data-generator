package com.bookatop.country.by;

import java.util.ArrayList;

public class ByRegion {

    private String name;

    private ArrayList<ByCity> cities;

    public ByRegion() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ByCity> getCities() {
        return cities;
    }

    public void setCities(ArrayList<ByCity> cities) {
        this.cities = cities;
    }
}
