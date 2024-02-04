package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CapCityMethod {
    public ArrayList<CapCity> getCapCities(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country and city(Name) information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<CapCity> getCapCitiesByContinent(Connection con, String capCityContinent)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population, country.Continent "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Continent = ?"
                            + "ORDER BY city.Population DESC ";
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,capCityContinent);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();

            // Extract capital city information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                cap.setCap_city_continent(rset.getString("country.Continent"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<CapCity> getCapCitiesByRegion(Connection con, String capCityRegion)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population, country.Region "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Region = ?"
                            + "ORDER BY city.Population DESC ";
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,capCityRegion);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();

            // Extract capital city information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                cap.setCap_city_continent(rset.getString("country.Region"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<CapCity> getTopTenCapCities(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY city.Population DESC LIMIT 10 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country and city(Name) information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<CapCity> getTopTenCapCitiesByContinent(Connection con, String capCityContinent)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population, country.Continent "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Continent = ?"
                            + "ORDER BY city.Population DESC LIMIT 10 ";
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,capCityContinent);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();

            // Extract capital city information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                cap.setCap_city_continent(rset.getString("country.Continent"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<CapCity> getTopTenCapCitiesByRegion(Connection con, String capCityRegion)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population, country.Region "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Region = ?"
                            + "ORDER BY city.Population DESC LIMIT 10 ";
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,capCityRegion);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();

            // Extract capital city information
            ArrayList<CapCity> capitals = new ArrayList<CapCity>();
            while (rset.next())
            {
                CapCity cap = new CapCity();
                cap.setCap_city_name(rset.getString("city.Name"));
                cap.setCap_city_country(rset.getString("country.Name"));
                cap.setCap_city_population(rset.getInt("city.Population"));
                cap.setCap_city_continent(rset.getString("country.Region"));
                capitals.add(cap);
            }
            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

}
