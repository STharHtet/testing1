package com.napier.sem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CityOutput {
    /**
     * Prints a list of World sorted by population.
     * @param dataoutput The list of countries to print.
     */
    public void printPopulation(ArrayList<City> dataoutput)
    {

        // Print top border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Print header
        System.out.println(String.format("| %-50s | %-50s | %-30s | %-35s |", "Name", "Country", "District", "Population"));
        // Print header-bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

        // Loop over all employees in the list
        for (City city : dataoutput)
        {
            // Format population with commas and three decimal places
            DecimalFormat numformat = new DecimalFormat("#,###,###");
            String formattedPopulation = numformat.format(city.getCity_population());

            String count_string =
                    String.format("| %-50s | %-50s | %-30s | %-35s |",
                            city.getCity_name(), city.getCountry_code(), city.getCity_district(), formattedPopulation, city.getCity_name());
            System.out.println(count_string);
        }
        // Print bottom border
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");
    }
}
