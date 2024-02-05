package com.napier.sem;

public class PeoplePop {
    private String people_pop_continent;

    private int people_population;

    public PeoplePop(String continent, int totalPopulation) {

    }

    public String getPeople_pop_continent() {
        return people_pop_continent;
    }

    public void setPeople_pop_continent(String people_pop_continent) {
        this.people_pop_continent = people_pop_continent;
    }

    public int getPeople_population() {
        return people_population;
    }

    public void setPeople_population(int people_population) {
        this.people_population = people_population;
    }
}
