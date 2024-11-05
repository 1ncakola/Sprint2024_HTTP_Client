package com.keyin.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RestCLI {
    private final Gson gson;

    public RestCLI() {
        this.gson = new Gson();
    }

    public void displayCities(String jsonResponse) {
        Type cityListType = new TypeToken<List<City>>(){}.getType();
        List<City> cities = gson.fromJson(jsonResponse, cityListType);

        System.out.println("List of Cities:");
        for (City city : cities) {
            System.out.println("ID: " + city.getId());
            System.out.println("Name: " + city.getName());
            System.out.println("State: " + (city.getState() != null ? city.getState() : "N/A"));
            System.out.println("Population: " + city.getPopulation());
            System.out.println("Airports: ");
            for (Airport airport : city.getAirports()) {
                System.out.println("  - " + airport.getName() + " (Code: " + airport.getCode() + ")");
            }
            System.out.println();
        }
    }


    private static class City {
        private int id;
        private String name;
        private String state;
        private int population;
        private List<Airport> airports;


        public int getId() { return id; }
        public String getName() { return name; }
        public String getState() { return state; }
        public int getPopulation() { return population; }
        public List<Airport> getAirports() { return airports; }
    }

    private static class Airport {
        private int id;
        private String name;
        private String code;
        private String city;
        private List<Object> aircraftList; // Assuming aircraftList contains unknown objects


        public int getId() { return id; }
        public String getName() { return name; }
        public String getCode() { return code; }
        public String getCity() { return city; }
    }
}
