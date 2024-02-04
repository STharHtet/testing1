package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * The following method is to call the classes to be run on the main Java class
     * @param args
     */
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Create a new country in the word object
        CountryMethod cw = new CountryMethod();
        CountryOutput coutput = new CountryOutput();

        // Create a new city in the word object
        CityMethod city = new CityMethod();
        CityOutput cityout = new CityOutput();

        // Array Countries, Region, Continents with the population largest to smallest
        // Extract country in the world from a class
        ArrayList<Country> countries = cw.getCountry(a.con);
        ArrayList<Country> continents = cw.getCountriesByContinent(a.con, "Asia");
        ArrayList<Country> region = cw.region_data(a.con,"caribbean");

        ArrayList<Country> tenCountries = cw.getTenCountry(a.con);
        ArrayList<Country> tenContinents = cw.getTenCountriesByContinent(a.con, "Asia");
        ArrayList<Country> tenRegions = cw.getTenCountriesByRegion(a.con,"Caribbean");

        ArrayList<City> cities = city.getCities(a.con);
        String city_continent = "Asia";
        ArrayList<City> citiesContinent = city.getCitiesByContinent(a.con, city_continent);
        String city_region = "Caribbean";
        ArrayList<City> citiesRegion = city.getCitiesByRegion(a.con, city_region);
        String city_country = "Myanmar";
        ArrayList<City> citiesCountry = city.getCitiesByCountry(a.con, city_country);
        String city_district = "Tokyo-to";
        ArrayList<City> citiesDistrict = city.getCitiesByDistrict(a.con, city_district);

        ArrayList<City> top_ten_cities = city.getTopTenCities(a.con);
        ArrayList<City> top_ten_cities_continent = city.getTopTenCitiesByContinent(a.con, city_continent);
        ArrayList<City> top_ten_cities_region = city.getTopTenCitiesByRegion(a.con, city_region);
        ArrayList<City> top_ten_cities_country = city.getTopTenCitiesByCountry(a.con, city_country);
        ArrayList<City> top_ten_cities_district = city.getTopTenCitiesByDistrict(a.con, city_district);


        // Printing data
//        System.out.println("All the countries in the world organised by largest population to smallest.");
//        coutput.printPopulation(countries);
//        System.out.println("All the countries in a continent organised by largest population to smallest. (Asia)");
//        coutput.printPopulation(continents);
//        System.out.println("All the countries in a region organised by largest population to smallest. (Caribbean)");
//        coutput.printPopulation(region);
//
//        System.out.println("The top 10 populated countries in the world.");
//        coutput.printPopulation(tenCountries);
//        System.out.println("The top 10 populated countries in a continent. (Asia)");
//        coutput.printPopulation(tenContinents);
//        System.out.println("The top 10 populated countries in a region. (Caribbean)");
//        coutput.printPopulation(tenRegions);

//        System.out.println("All the cities in the world organised by largest population to smallest.");
//        cityout.printPopulation(cities);
        System.out.println("All the cities in a continent organised by largest population to smallest. (" + city_continent + ")");
        cityout.printPopulation(citiesContinent);
        System.out.println("All the cities in a region organised by largest population to smallest. (" + city_region + ")");
        cityout.printPopulation(citiesRegion);
        System.out.println("All the cities in a country organised by largest population to smallest. (" + city_country + ")");
        cityout.printPopulation(citiesCountry);
        System.out.println("All the cities in a district organised by largest population to smallest. (" + city_district + ")");
        cityout.printPopulation(citiesDistrict);

        System.out.println("The top 10 populated cities in the world.");
        cityout.printPopulation(top_ten_cities);
        System.out.println("The top 10 populated cities in a continent. (" + city_continent + ")");
        cityout.printPopulation(top_ten_cities_continent);
        System.out.println("The top 10 populated cities in a region. (" + city_region + ")");
        cityout.printPopulation(top_ten_cities_region);
        System.out.println("The top 10 populated cities in a country. (" + city_country + ")");
        cityout.printPopulation(top_ten_cities_country);
        System.out.println("The top 10 populated cities in a district. (" + city_district + ")");
        cityout.printPopulation(top_ten_cities_district);



        // Disconnect from database
        a.disconnect();
    }
}