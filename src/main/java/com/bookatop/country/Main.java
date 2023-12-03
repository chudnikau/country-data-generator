package com.bookatop.country;

import com.bookatop.country.generator.ByCityGenerator;
import com.bookatop.country.generator.CountryGenerator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ByCityGenerator cityGenerator = new ByCityGenerator();
        cityGenerator.generate();

        CountryGenerator countryGenerator = new CountryGenerator();
        countryGenerator.generate();
    }
}
