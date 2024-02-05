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

        // Create a new capital city in the word object
        CapCityMethod capcity = new CapCityMethod();
        CapCityOutput capcityout = new CapCityOutput();

        // Input for Continent, Region, Country and District
        String input_continent = "asia";
        String input_region = "Caribbean";
        String input_country = "Myanmar";
        String input_district = "Tokyo-to";

        // Array Countries, Region, Continents with the population largest to smallest
        // Extract country in the world from a class
        ArrayList<Country> countries = cw.getCountry(a.con);
        ArrayList<Country> continents = cw.getCountriesByContinent(a.con, "Asia");
        ArrayList<Country> region = cw.region_data(a.con,"caribbean");

        // Extract top ten countries
        ArrayList<Country> tenCountries = cw.getTenCountry(a.con);
        ArrayList<Country> tenContinents = cw.getTenCountriesByContinent(a.con, "Asia");
        ArrayList<Country> tenRegions = cw.getTenCountriesByRegion(a.con,"Caribbean");

        // Extract cities
        ArrayList<City> cities = city.getCities(a.con);
        ArrayList<City> citiesContinent = city.getCitiesByContinent(a.con, input_continent);
        ArrayList<City> citiesRegion = city.getCitiesByRegion(a.con, input_region);
        ArrayList<City> citiesCountry = city.getCitiesByCountry(a.con, input_country);
        ArrayList<City> citiesDistrict = city.getCitiesByDistrict(a.con, input_district);

        // Extract top ten cities
        ArrayList<City> top_ten_cities = city.getTopTenCities(a.con);
        ArrayList<City> top_ten_cities_continent = city.getTopTenCitiesByContinent(a.con, input_continent);
        ArrayList<City> top_ten_cities_region = city.getTopTenCitiesByRegion(a.con, input_region);
        ArrayList<City> top_ten_cities_country = city.getTopTenCitiesByCountry(a.con, input_country);
        ArrayList<City> top_ten_cities_district = city.getTopTenCitiesByDistrict(a.con, input_district);

        // Extract capital cities
        ArrayList<CapCity> capcities = capcity.getCapCities(a.con);
        ArrayList<CapCity> capcitiesContinent = capcity.getCapCitiesByContinent(a.con, input_continent);
        ArrayList<CapCity> capcitiesRegion = capcity.getCapCitiesByRegion(a.con, input_region);

        // Extract top ten capital cities
        ArrayList<CapCity> top_ten_capcities = capcity.getTopTenCapCities(a.con);
        ArrayList<CapCity> top_ten_capcities_continent = capcity.getTopTenCapCitiesByContinent(a.con, input_continent, 10);
        ArrayList<CapCity> top_ten_capcities_region = capcity.getTopTenCapCitiesByRegion(a.con, input_region);



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
//        System.out.println("All the cities in a continent organised by largest population to smallest. (" + input_continent + ")");
//        cityout.printPopulation(citiesContinent);
//        System.out.println("All the cities in a region organised by largest population to smallest. (" + input_region + ")");
//        cityout.printPopulation(citiesRegion);
//        System.out.println("All the cities in a country organised by largest population to smallest. (" + input_country + ")");
//        cityout.printPopulation(citiesCountry);
//        System.out.println("All the cities in a district organised by largest population to smallest. (" + input_district + ")");
//        cityout.printPopulation(citiesDistrict);
//
//        System.out.println("The top 10 populated cities in the world.");
//        cityout.printPopulation(top_ten_cities);
//        System.out.println("The top 10 populated cities in a continent. (" + input_continent + ")");
//        cityout.printPopulation(top_ten_cities_continent);
//        System.out.println("The top 10 populated cities in a region. (" + input_region + ")");
//        cityout.printPopulation(top_ten_cities_region);
//        System.out.println("The top 10 populated cities in a country. (" + input_country + ")");
//        cityout.printPopulation(top_ten_cities_country);
//        System.out.println("The top 10 populated cities in a district. (" + input_district + ")");
//        cityout.printPopulation(top_ten_cities_district);

        System.out.println("All the capital cities in the world organised by largest population to smallest.");
        capcityout.printPopulation(capcities);
        System.out.println("All the capital cities in a continent organised by largest population to smallest. (" + input_continent + ")");
        capcityout.printPopulation(capcitiesContinent);
        System.out.println("All the capital cities in a region organised by largest population to smallest. (" + input_region + ")");
        capcityout.printPopulation(capcitiesRegion);

        System.out.println("The top 10 populated capital cities in the world.");
        capcityout.printPopulation(top_ten_capcities);
        System.out.println("The top 10 populated capital cities in a continent. (" + input_continent + ")");
        capcityout.printPopulation(top_ten_capcities_continent);
        System.out.println("The top 10 populated capital cities in a region. (" + input_region + ")");
        capcityout.printPopulation(top_ten_capcities_region);

        





        // Disconnect from database
        a.disconnect();
    }
}