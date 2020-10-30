package pl.sda.bootcamp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private List<String> cities;

    public CityService() {
        this.initializeCitiesList();
    }

    public List<String> getCities() {
        return this.cities;
    }

    private void initializeCitiesList() {
        this.cities = new ArrayList<>();
        this.cities.add("Gdańsk");
        this.cities.add("Warszawa");
        this.cities.add("Wrocław");
        this.cities.add("Szczecin");
    }
}
