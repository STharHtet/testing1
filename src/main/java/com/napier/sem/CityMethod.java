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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population, country.Name AS CountryName "
                            + "FROM city "
                            + "INNER JOIN country ON city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cit = new City();
                cit.setCity_name(rset.getString("Name"));
                cit.setCountry_code(rset.getString("CountryCode"));
                cit.setCity_district(rset.getString("District"));
                cit.setCity_population(rset.getInt("Population"));
//                cit.setCountryName(rset.getString("CountryName"));
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
