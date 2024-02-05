package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class PeoplePopMethod {

    public ArrayList<PeoplePop> getPeoplePopulationByContinent(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, SUM(country.Population) AS TotalPopulation "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code "
                            + "GROUP BY country.Continent";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract population information by continent
            ArrayList<PeoplePop> populationByContinent = new ArrayList<>();
            while (rset.next())
            {
                String continent = rset.getString("country.Continent");
                int totalPopulation = rset.getInt("TotalPopulation");
                populationByContinent.add(new PeoplePop(continent, totalPopulation));
            }
            return populationByContinent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population by continent");
            return null;
        }
    }
}
