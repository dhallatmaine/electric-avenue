package ea.maps;

import java.util.Map;

public class City {

    private String name;
    private Region region;
    private Map<City, Integer> connectedCities;

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public City withRegion(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Map<City, Integer> getConnectedCities() {
        return connectedCities;
    }

    public City withConnectedCities(Map<City, Integer> connectedCities) {
        this.connectedCities = connectedCities;
        return this;
    }

    public void setConnectedCities(Map<City, Integer> connectedCities) {
        this.connectedCities = connectedCities;
    }

}