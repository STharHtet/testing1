package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Country_world {
    public ArrayList<country> getCountry(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY country.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<country> countries = new ArrayList<country>();
            while (rset.next())
            {
                country cou = new country();
                cou.setCountry_code(rset.getString("country.Code"));
                cou.setCountry_name(rset.getString("country.Name"));
                cou.setContinent(rset.getString("country.Continent"));
                cou.setRegion(rset.getString("country.Region"));
                cou.setPopulation(rset.getInt("country.Population"));
                cou.setCity_name(rset.getString("city.Name"));
                countries.add(cou);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     * @param countries The list of countries to print.
     */
    public void printPopulation(ArrayList<country> countries)
    {
        // What the feature is
        System.out.println("For World");
        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (country count : countries)
        {
            // Format population with commas and three decimal places
            DecimalFormat numformat = new DecimalFormat("#,###,###");
            String formattedPopulation = numformat.format(count.getPopulation());

            String count_string =
                    String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |",
                            count.getCountry_code(), count.getCountry_name(), count.getContinent(), count.getRegion(), formattedPopulation, count.getCity_name());
            System.out.println(count_string);
        }
        // Print bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

    }

    public ArrayList<country> getCountriesByContinent(Connection con, String inContinent)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Continent = ?"
                            + "ORDER BY country.Population DESC ";
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,inContinent);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();
            // Extract employee information
            ArrayList<country> countries = new ArrayList<country>();
            while (rset.next())
            {
                country cou = new country();
                cou.setCountry_code(rset.getString("country.Code"));
                cou.setCountry_name(rset.getString("country.Name"));
                cou.setContinent(rset.getString("country.Continent"));
                cou.setRegion(rset.getString("country.Region"));
                cou.setPopulation(rset.getInt("country.Population"));
                cou.setCity_name(rset.getString("city.Name"));
                countries.add(cou);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     * @param continents The list of countries to print.
     */
    public void printContinentPopulation(ArrayList<country> continents)
    {
        // What the feature is
        System.out.println("For Continent");
        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (country cont : continents)
        {
            // Format population with commas and three decimal places
            DecimalFormat numformat = new DecimalFormat("#,###,###");
            String formattedPopulation = numformat.format(cont.getPopulation());

            String count_string =
                    String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |",
                            cont.getCountry_code(), cont.getCountry_name(), cont.getContinent(), cont.getRegion(), formattedPopulation, cont.getCity_name());
            System.out.println(count_string);
        }
        // Print bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

    }

    /**
     * The following code is method for the region
     */
    public ArrayList<country> region_data(Connection con, String regionn)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Region = ? "
                            + "ORDER BY Region ASC ,Population DESC ";

            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,regionn);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();
            // Extract employee information
            ArrayList<country> countries = new ArrayList<country>();
            while (rset.next())
            {
                country cou = new country();
                cou.setCountry_code(rset.getString("country.Code"));
                cou.setCountry_name(rset.getString("country.Name"));
                cou.setContinent(rset.getString("country.Continent"));
                cou.setRegion(rset.getString("country.Region"));
                cou.setPopulation(rset.getInt("country.Population"));
                cou.setCity_name(rset.getString("city.Name"));
                countries.add(cou);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * The following code is the print the region
     */
    public void printRegion(ArrayList<country> region)
    {
        // What the feature is
        System.out.println("For Region");
        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (country count : region)
        {
            // Format population with commas and three decimal places
            DecimalFormat numformat = new DecimalFormat("#,###,###");
            String formattedPopulation = numformat.format(count.getPopulation());

            String count_string =
                    String.format("| %-5s | %-50s | %-20s | %-30s | %-20s | %-35s |",
                            count.getCountry_code(), count.getCountry_name(), count.getContinent(), count.getRegion(), formattedPopulation, count.getCity_name());
            System.out.println(count_string);
        }
        // Print bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

    }
}
