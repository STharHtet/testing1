package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CityMethod {
    public ArrayList<City> getCity(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.Continent, city.District, city.Population, country.Name "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cit = new City();
                cit.setCity_name(rset.getString("city.Name"));
                cit.setCountry_code(rset.getString("city.CountryCode"));
                cit.setCountry_name(rset.getString("country.Name"));
                cit.setCity_continent(rset.getString("country.Continent"));
                cit.setCity_district(rset.getString("city.District"));
                cit.setCity_population(rset.getInt("city.Population"));
                cities.add(cit);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public ArrayList<City> getCitiesByContinent(Connection con, String cityContinent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.Continent, city.District, city.Population, country.Name "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code AND country.Continent = ? "
                            + "ORDER BY city.Population DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cit = new City();
                cit.setCity_name(rset.getString("city.Name"));
                cit.setCountry_code(rset.getString("city.CountryCode"));
                cit.setCountry_name(rset.getString("country.Name"));
                cit.setCity_continent(rset.getString("country.Continent"));
                cit.setCity_district(rset.getString("city.District"));
                cit.setCity_population(rset.getInt("city.Population"));
                cities.add(cit);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }




}
