package com.napier.sem;

import com.napier.sem.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
class countryTest {
    private static App app;
    private static CountryOutput countryout;

    private static CountryMethod countrymethod;

    @BeforeAll
    static void init() {
        app = new App();
        countryout = new CountryOutput();
        countrymethod = new CountryMethod();
    }

    @Test
    void countryListNullTest(){
        ArrayList<Country> list = null;
        countryout.printPopulation(list);
    }

    @Test
    void noCountryTest(){
        ArrayList<Country> list = new ArrayList<Country>();
        countryout.printPopulation(list);
    }

    @Test
    void nullCountryTest(){
        ArrayList<Country> list = new ArrayList<Country>();
        Country cou = new Country();
        cou.setCountry_code("ANT");
        cou.setCountry_name("Netherlands Antilles");
        cou.setContinent("North America");
        cou.setRegion("Caribbean");
        cou.setPopulation(217000);
        cou.setCity_name("Willemstad");
        Country cou1 = null;
        list.add(cou);
        list.add(cou1);
        countryout.printPopulation(list);
    }
    @Test
    void countryListInput(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.setCountry_code("ANT");
        cou.setCountry_name("Netherlands Antilles");
        cou.setContinent("North America");
        cou.setRegion("Caribbean");
        cou.setPopulation(217000);
        cou.setCity_name("Willemstad");
        countries.add(cou);
        countryout.printPopulation(countries);
    }
}

