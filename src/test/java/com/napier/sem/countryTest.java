package com.napier.sem;

import com.napier.sem.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class countryTest {
    private static App app;
    private static CountryOutput countryout;
    private static CountryMethod countrymethod;
    private static CityOutput cityout;
    private static CityMethod citymethod;


    @BeforeAll
    static void init() {
        app = new App();
        countryout = new CountryOutput();
        countrymethod = new CountryMethod();
        cityout = new CityOutput();
        citymethod = new CityMethod();
    }

    @Test
    void countryListNullTest(){
        ArrayList<Country> list = null;
        countryout.printPopulation(list);
    }

    @Test
    void noCountryTest(){
        ArrayList<Country> list = new ArrayList<Country>();
        countryout.printPopulation(list);
    }

    @Test
    void nullCountryTest(){
        ArrayList<Country> list = new ArrayList<Country>();
        Country cou = new Country();
        cou.setCountry_code("ANT");
        cou.setCountry_name("Netherlands Antilles");
        cou.setContinent("North America");
        cou.setRegion("Caribbean");
        cou.setPopulation(217000);
        cou.setCity_name("Willemstad");

        Country cou1 = null;

        Country cou2 = new Country();
        cou2.setCountry_code("MMR");
        cou2.setCountry_name("Myanmar");
        cou2.setContinent("Asia");
        cou2.setRegion("Southeast Asia");
        cou2.setPopulation(337000);
        cou2.setCity_name("Yangon");

        list.add(cou);
        list.add(cou1);
        list.add(cou2);
        countryout.printPopulation(list);
    }
    @Test
    void countryListInput(){
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.setCountry_code("ANT");
        cou.setCountry_name("Netherlands Antilles");
        cou.setContinent("North America");
        cou.setRegion("Caribbean");
        cou.setPopulation(217000);
        cou.setCity_name("Willemstad");
        countries.add(cou);
        countryout.printPopulation(countries);
    }

    @Test
    void cityListNullTest(){
        ArrayList<City> citylist = null;
        cityout.printPopulation(citylist);
    }

    @Test
    void noCityTest(){
        ArrayList<City> citylist = new ArrayList<City>();
        cityout.printPopulation(citylist);
    }

    @Test
    void nullCityTest(){
        ArrayList<City> citylist = new ArrayList<City>();
        City cit = new City();
        cit.setCity_name("Yangon");
        cit.setCountry_code("MMR");
        cit.setCountry_name("Myanmar");
        cit.setCity_continent("Asia");
        cit.setCity_district("Yangon");
        cit.setCity_population(123500);
        citylist.add(cit);

        City cit1 = null;
        citylist.add(cit1);

        City cit2 = new City();
        cit2.setCity_name("Tokyo");
        cit2.setCountry_code("JPN");
        cit2.setCountry_name("Japan");
        cit2.setCity_continent("Asia");
        cit2.setCity_district("Tokyo");
        cit2.setCity_population(23700);
        citylist.add(cit2);

        cityout.printPopulation(citylist);
    }

    @Test
    void citListInput(){
        ArrayList<City> citylist = new ArrayList<City>();
        City cit = new City();
        cit.setCity_name("Yangon");
        cit.setCountry_code("MMR");
        cit.setCountry_name("Myanmar");
        cit.setCity_continent("Asia");
        cit.setCity_region("Southeast Asia");
        cit.setCity_district("Yangon");
        cit.setCity_population(123500);
        citylist.add(cit);
        cityout.printPopulation(citylist);
    }

    @Test
    public void testGetCountry() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("country.Code"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");
        when(mockResultSet.getInt(eq("country.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");

        // Create an instance of the CountryMethod class
        CountryMethod countryMethod = new CountryMethod();

        // Call the method with the mocked connection
        ArrayList<Country> countries = countryMethod.getCountry(mockConnection);

        // Verify the results
        assertEquals(1, countries.size());

        Country country = countries.get(0);
        assertEquals("ABC", country.getCountry_code());
        assertEquals("TestCountry", country.getCountry_name());
        assertEquals("TestContinent", country.getContinent());
        assertEquals("TestRegion", country.getRegion());
        assertEquals(1000000, country.getPopulation());
        assertEquals("TestCity", country.getCity_name());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCountriesByContinent() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("country.Code"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");
        when(mockResultSet.getInt(eq("country.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");

        // Create an instance of the CountryMethod class
        CountryMethod countryMethod = new CountryMethod();

        // Call the method with the mocked connection
        ArrayList<Country> countries = countryMethod.getCountriesByContinent(mockConnection, "TestContinent");

        // Verify the results
        assertEquals(1, countries.size());

        Country country = countries.get(0);
        assertEquals("ABC", country.getCountry_code());
        assertEquals("TestCountry", country.getCountry_name());
        assertEquals("TestContinent", country.getContinent());
        assertEquals("TestRegion", country.getRegion());
        assertEquals(1000000, country.getPopulation());
        assertEquals("TestCity", country.getCity_name());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).setString(eq(1), eq("TestContinent"));
        verify(mockStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testRegionData() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("country.Code"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");
        when(mockResultSet.getInt(eq("country.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");

        // Create an instance of the CountryMethod class
        CountryMethod countryMethod = new CountryMethod();

        // Call the method with the mocked connection
        ArrayList<Country> countries = countryMethod.region_data(mockConnection, "TestRegion");

        // Verify the results
        assertEquals(1, countries.size());

        Country country = countries.get(0);
        assertEquals("ABC", country.getCountry_code());
        assertEquals("TestCountry", country.getCountry_name());
        assertEquals("TestContinent", country.getContinent());
        assertEquals("TestRegion", country.getRegion());
        assertEquals(1000000, country.getPopulation());
        assertEquals("TestCity", country.getCity_name());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockStatement, times(1)).setString(eq(1), eq("TestRegion"));
        verify(mockStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }





}

