package ea.data;

import ea.maps.City;

import java.util.List;
import java.util.Map;

public class Player {

    private Long id;
    private String name;
    private Integer money;
    private List<PowerPlant> powerPlants;
    private List<City> cities;
    private Map<PowerPlant, List<Resource>> resources;
    private Color color;

    public Long getId() {
        return id;
    }

    public Player withId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMoney() {
        return money;
    }

    public Player withMoney(Integer money) {
        this.money = money;
        return this;
    }

    public List<PowerPlant> getPowerPlants() {
        return powerPlants;
    }

    public Player withPowerPlants(List<PowerPlant> powerPlants) {
        this.powerPlants = powerPlants;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public Player withCities(List<City> cities) {
        this.cities = cities;
        return this;
    }

    public Map<PowerPlant, List<Resource>> getResources() {
        return resources;
    }

    public Player withResources(Map<PowerPlant, List<Resource>> resources) {
        this.resources = resources;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Player withColor(Color color) {
        this.color = color;
        return this;
    }
}