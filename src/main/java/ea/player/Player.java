package ea.player;

import ea.resource.Resource;
import ea.maps.City;
import ea.powerplant.PowerPlant;

import java.util.List;
import java.util.Map;

public class Player {

    private String userId;
    private Integer money;
    private List<PowerPlant> powerPlants;
    private List<City> cities;
    private Map<Integer, List<Resource>> resources;
    private Color color;

    public String getUserId() {
        return userId;
    }

    public Player withUserId(String userId) {
        this.userId = userId;
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

    public Map<Integer, List<Resource>> getResources() {
        return resources;
    }

    public Player withResources(Map<Integer, List<Resource>> resources) {
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