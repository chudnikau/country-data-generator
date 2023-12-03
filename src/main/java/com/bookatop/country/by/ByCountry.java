package com.bookatop.country.by;

import java.util.ArrayList;

public class ByCountry {

    private String name;

    private ArrayList<ByRegion> regions;

    public ByCountry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<ByRegion> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<ByRegion> regions) {
        this.regions = regions;
    }
}
