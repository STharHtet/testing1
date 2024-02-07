package com.napier.sem;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
public class cityTest {
    private static App app;
    private static CityOutput cityout;
    private static CityMethod citymethod;

    @BeforeAll
    static void init(){
        app = new App();
        cityout = new CityOutput();
        citymethod = new CityMethod();
    }

    @Test
    void cityListNullTest(){
        ArrayList<City> citylist = null;
        cityout.printPopulation(citylist);
    }

    @Test
    void noCityTest(){
        ArrayList<City> citylist = new ArrayList<City>();
        cityout.printPopulation(citylist);
    }

    @Test
    void nullCityTest(){
        ArrayList<City> citylist = new ArrayList<City>();
        City cit = new City();
        cit.setCity_name("Yangon");
        cit.setCountry_code("MMR");
        cit.setCountry_name("Myanmar");
        cit.setCity_continent("Asia");
        cit.setCity_district("Yangon");
        cit.setCity_population(123500);
        citylist.add(cit);

        City cit1 = null;
        citylist.add(cit1);

        City cit2 = new City();
        cit2.setCity_name("Tokyo");
        cit2.setCountry_code("JPN");
        cit2.setCountry_name("Japan");
        cit2.setCity_continent("Asia");
        cit2.setCity_district("Tokyo");
        cit2.setCity_population(23700);
        citylist.add(cit2);

        cityout.printPopulation(citylist);
    }

    @Test
    void countryListInput(){
        ArrayList<City> citylist = new ArrayList<City>();
        City cit = new City();
        cit.setCity_name("Yangon");
        cit.setCountry_code("MMR");
        cit.setCountry_name("Myanmar");
        cit.setCity_continent("Asia");
        cit.setCity_region("Southeast Asia");
        cit.setCity_district("Yangon");
        cit.setCity_population(123500);
        citylist.add(cit);
        cityout.printPopulation(citylist);
    }

}
