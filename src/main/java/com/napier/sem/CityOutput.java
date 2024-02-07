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

        try {

            if (dataoutput == null) {
                System.out.println("The list is null");
            } else if (dataoutput.size() == 0) {
                System.out.println("There is no city");
            } else {

                // Print top border
                System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

                // Print header
                // Put Continent (30) or Region (30) below for display
                System.out.println(String.format("| %-50s | %-50s | %-30s  | %-35s |", "Name", "Country", "District", "Population"));
                // Print header-bottom border
                System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

                // Loop over all cities in the list
                for (City city : dataoutput) {
                    if (city == null) {
                        System.out.println("City is null");
                    } else {
                        // Format population with commas and three decimal places
                        DecimalFormat numformat = new DecimalFormat("#,###,###");
                        String formattedPopulation = numformat.format(city.getCity_population());

                        // Add country code in parentheses after the country name
                        String countryString = String.format("%s (%s)", city.getCountry_name(), city.getCountry_code());

                        // Put city.getCity_continent() to display Continent data
                        // Put city.getCity_region() to display Region data
                        String count_string =
                                String.format("| %-50s | %-50s | %-30s  | %-35s |",
                                        city.getCity_name(), countryString, city.getCity_district(), formattedPopulation);
                        System.out.println(count_string);
                    }
                }
                // Print bottom border
                System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");
            }
        }

        catch (NullPointerException ne){
            System.out.println("The arraylist is empty");
        }
        catch (Exception e){
            System.out.println("Error occured because "  + e);
        }

        }

}
