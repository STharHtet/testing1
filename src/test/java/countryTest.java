import com.napier.sem.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
class countryTest {
    private static App app;
    private static CountryOutput countryout;

    private static CountryMethod countrymethod;

    @BeforeAll
    static void init() {
        app = new App();
        countryout = new CountryOutput();
        countrymethod = new CountryMethod();
    }

    @Test
    void countryListTest(){
        ArrayList<Country> list = new ArrayList<Country>();
        list.add(null);
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
}

