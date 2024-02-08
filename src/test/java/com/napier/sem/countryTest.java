package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class countryTest {
    private static App app;
    private static CountryOutput countryout;
    private static CountryMethod countrymethod;
    private static CityOutput cityout;
    private static CityMethod citymethod;
    private static CapCityOutput capitalout;


    @BeforeAll
    static void init() {
        app = new App();
        countryout = new CountryOutput();
        countrymethod = new CountryMethod();

        cityout = new CityOutput();
        citymethod = new CityMethod();

        capitalout = new CapCityOutput();
    }

    @Test
    void countryListNullTest(){
        ArrayList<Country> countrylist = null;
        countryout.printPopulation(countrylist);
    }

    @Test
    void noCountryTest(){
        ArrayList<Country> countrylist = new ArrayList<Country>();
        countryout.printPopulation(countrylist);
    }

    @Test
    void nullCountryTest(){
        ArrayList<Country> countrylist = new ArrayList<Country>();
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

        countrylist.add(cou);
        countrylist.add(cou1);
        countrylist.add(cou2);
        countryout.printPopulation(countrylist);
    }
    @Test
    void countryListInput(){
        ArrayList<Country> countrylist = new ArrayList<Country>();
        Country cou = new Country();
        cou.setCountry_code("ANT");
        cou.setCountry_name("Netherlands Antilles");
        cou.setContinent("North America");
        cou.setRegion("Caribbean");
        cou.setPopulation(217000);
        cou.setCity_name("Willemstad");
        countrylist.add(cou);
        countryout.printPopulation(countrylist);
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
    void capitalListNullTest(){
        ArrayList<CapCity> capitallist = null;
        capitalout.printPopulation(capitallist);
    }

    @Test
    void noCapitalTest(){
        ArrayList<CapCity> capitallist = new ArrayList<CapCity>();
        capitalout.printPopulation(capitallist);
    }

    @Test
    void nullCapitalTest(){
        ArrayList<CapCity> capitallist = new ArrayList<CapCity>();
        CapCity cap = new CapCity();
        cap.setCap_city_name("Yangon");
        cap.setCap_city_country("Myanmar");
        cap.setCap_city_population(125000);

        CapCity cap1 = null;
        capitallist.add(cap1);

        CapCity cap2 = new CapCity();
        cap2.setCap_city_name("Tokyo");
        cap2.setCap_city_country("Japan");
        cap2.setCap_city_population(255000);

        capitalout.printPopulation(capitallist);
    }

    @Test
    void capitalListInput(){
        ArrayList<CapCity> capitallist = new ArrayList<CapCity>();
        CapCity cap = new CapCity();
        cap.setCap_city_name("Yangon");
        cap.setCap_city_country("Myanmar");
        cap.setCap_city_population(125000);
        capitallist.add(cap);
        capitalout.printPopulation(capitallist);
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

    @Test
    public void testGetTenCountry() throws Exception {
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

        // Call the method with the mocked connection and limit
        ArrayList<Country> countries = countryMethod.getTenCountry(mockConnection, 10);

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
    public void testGetTenCountriesByContinent() throws Exception {
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

        // Call the method with the mocked connection, continent, and limit
        ArrayList<Country> countries = countryMethod.getTenCountriesByContinent(mockConnection, "TestContinent", 10);

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
    public void testGetTenCountriesByRegion() throws Exception {
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

        // Call the method with the mocked connection, region, and limit
        ArrayList<Country> countries = countryMethod.getTenCountriesByRegion(mockConnection, "TestRegion", 10);

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

    @Test
    public void testGetCities() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CountryMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getCities(mockConnection);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestContinent", city.getCity_continent());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCitiesByContinent() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getCitiesByContinent(mockConnection, "TestContinent");

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestContinent", city.getCity_continent());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(eq(1), eq("TestContinent"));
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCitiesByRegion() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getCitiesByRegion(mockConnection, "TestRegion");

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestRegion", city.getCity_region());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(eq(1), eq("TestRegion"));
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCitiesByCountry() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getCitiesByCountry(mockConnection, "TestCountry");

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(eq(1), eq("TestCountry"));
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCitiesByDistrict() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getCitiesByDistrict(mockConnection, "TestDistrict");

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(eq(1), eq("TestDistrict"));
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCities() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getTopTenCities(mockConnection, 10);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestContinent", city.getCity_continent());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCitiesByContinent() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getTopTenCitiesByContinent(mockConnection, "TestContinent", 10);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestContinent", city.getCity_continent());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestContinent");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCitiesByRegion() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getTopTenCitiesByRegion(mockConnection, "TestRegion", 10);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestRegion", city.getCity_region());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestRegion");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCitiesByCountry() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getTopTenCitiesByCountry(mockConnection, "TestCountry", 10);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestCountry");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCitiesByDistrict() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("city.CountryCode"))).thenReturn("ABC");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getString(eq("city.District"))).thenReturn("TestDistrict");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CityMethod class
        CityMethod cityMethod = new CityMethod();

        // Call the method with the mocked connection
        ArrayList<City> cities = cityMethod.getTopTenCitiesByDistrict(mockConnection, "TestDistrict", 10);

        // Verify the results
        assertEquals(1, cities.size());

        City city = cities.get(0);
        assertEquals("TestCity", city.getCity_name());
        assertEquals("ABC", city.getCountry_code());
        assertEquals("TestCountry", city.getCountry_name());
        assertEquals("TestDistrict", city.getCity_district());
        assertEquals(1000000, city.getCity_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestDistrict");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCapCities() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getCapCities(mockConnection);

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCapCitiesByContinent() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getCapCitiesByContinent(mockConnection, "TestContinent");

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());
        assertEquals("TestContinent", capCity.getCap_city_continent());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestContinent");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetCapCitiesByRegion() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getCapCitiesByRegion(mockConnection, "TestRegion");

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());
        assertEquals("TestRegion", capCity.getCap_city_continent());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestRegion");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCapCities() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getTopTenCapCities(mockConnection, 10);

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCapCitiesByContinent() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("country.Continent"))).thenReturn("TestContinent");

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getTopTenCapCitiesByContinent(mockConnection, "TestContinent", 10);

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());
        assertEquals("TestContinent", capCity.getCap_city_continent());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestContinent");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTopTenCapCitiesByRegion() throws Exception {
        // Mock the objects needed for the test
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Set up the behavior of the mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString(eq("city.Name"))).thenReturn("TestCity");
        when(mockResultSet.getString(eq("country.Name"))).thenReturn("TestCountry");
        when(mockResultSet.getInt(eq("city.Population"))).thenReturn(1000000);
        when(mockResultSet.getString(eq("country.Region"))).thenReturn("TestRegion");

        // Create an instance of the CapCityMethod class
        CapCityMethod capCityMethod = new CapCityMethod();

        // Call the method with the mocked connection
        ArrayList<CapCity> capitals = capCityMethod.getTopTenCapCitiesByRegion(mockConnection, "TestRegion", 10);

        // Verify the results
        assertEquals(1, capitals.size());

        CapCity capCity = capitals.get(0);
        assertEquals("TestCity", capCity.getCap_city_name());
        assertEquals("TestCountry", capCity.getCap_city_country());
        assertEquals(1000000, capCity.getCap_city_population());
        assertEquals("TestRegion", capCity.getCap_city_continent());

        // Verify that the necessary methods were called on the mocks
        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).setString(1, "TestRegion");
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }


}

