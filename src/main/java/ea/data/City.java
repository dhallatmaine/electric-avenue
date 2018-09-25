package ea.data;

import java.util.Map;

public class City {

    private String name;
    private String region;
    private Player phase1;
    private Player phase2;
    private Player phase3;
    private Map<City, Integer> connectedCities;

    public String getName() {
        return name;
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public City withRegion(String region) {
        this.region = region;
        return this;
    }

    public Player getPhase1() {
        return phase1;
    }

    public void setPhase1(Player phase1) {
        this.phase1 = phase1;
    }

    public City withPhase1(Player phase1) {
        this.phase1 = phase1;
        return this;
    }

    public Player getPhase2() {
        return phase2;
    }

    public City withPhase2(Player phase2) {
        this.phase2 = phase2;
        return this;
    }

    public Player getPhase3() {
        return phase3;
    }

    public City withPhase3(Player phase3) {
        this.phase3 = phase3;
        return this;
    }

    public Map<City, Integer> getConnectedCities() {
        return connectedCities;
    }

    public City withConnectedCities(Map<City, Integer> connectedCities) {
        this.connectedCities = connectedCities;
        return this;
    }

}