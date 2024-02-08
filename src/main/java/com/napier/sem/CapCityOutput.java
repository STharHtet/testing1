package com.napier.sem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CapCityOutput {

    public void printPopulation(ArrayList<CapCity> dataoutput)
    {

        try {
            if (dataoutput == null) {
                System.out.println("The list is null");
            } else if (dataoutput.size() == 0) {
                System.out.println("There is no capital cities");
            } else {

                // Print top border
                System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

                // Print header
                // Put Continent (30) or Region (30) below for display
                System.out.println(String.format("| %-50s | %-50s | %-20s |", "Name", "Country", "Population"));
                // Print header-bottom border
                System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+---------------------+--");

                // Loop over all capital cities in the list
                for (CapCity cap : dataoutput) {

                    if (cap == null) {
                        System.out.println("Capital city is null");
                    } else {
                        // Format population with commas and three decimal places
                        DecimalFormat numformat = new DecimalFormat("#,###,###");
                        String formattedPopulation = numformat.format(cap.getCap_city_population());

                        // Put cap.getCap_city_continent() to display Continent data
                        // Put cap.getCap_city_region() to display Region data
                        String count_string =
                                String.format("| %-50s | %-50s | %-20s |",
                                        cap.getCap_city_name(), cap.getCap_city_country(), formattedPopulation);
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
            System.out.println("Error occured because " + e);
        }
        }



}
