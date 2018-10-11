package ea.data;

import java.util.List;
import java.util.Map;

public class Player {

    private Long id;
    private String name;
    private Integer money;
    private List<PowerPlant> powerPlants;
    private List<City> cities;
    private Map<PowerPlant, List<Resource>> resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public List<PowerPlant> getPowerPlants() {
        return powerPlants;
    }

    public void setPowerPlants(List<PowerPlant> powerPlants) {
        this.powerPlants = powerPlants;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Map<PowerPlant, List<Resource>> getResources() {
        return resources;
    }

    public void setResources(Map<PowerPlant, List<Resource>> resources) {
        this.resources = resources;
    }

}