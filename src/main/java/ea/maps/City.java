package ea.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class City {

    private String name;
    private Region region;
    private List<Integer> districts;
    private List<Connection> connectedCities;

    public City() { }

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

    public List<Integer> getDistricts() {
        return districts;
    }

    public City withDistricts(List<Integer> districts) {
        this.districts = districts;
        return this;
    }

    public void setDistricts(List<Integer> districts) {
        this.districts = districts;
    }

    public List<Connection> getConnectedCities() {
        return connectedCities;
    }

    public City withConnectedCities(List<Connection> connectedCities) {
        this.connectedCities = connectedCities;
        return this;
    }

    @JsonProperty("connections")
    public void setConnectedCities(List<Connection> connectedCities) {
        this.connectedCities = connectedCities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(name).append(";").append(region).append(";");
        sb.append("districts:");

        for (int i = 0; i < districts.size(); i++) {
            if (i != (districts.size() - 1))
                sb.append(districts.get(i)).append(",");
            else
                sb.append(districts.get(i));
        }

        sb.append(";").append("connectedCities:");

        for (int i = 0; i < connectedCities.size(); i++) {
            if (i != (connectedCities.size() - 1))
                sb.append(connectedCities.get(i)).append(",");
            else
                sb.append(connectedCities.get(i));
        }

        sb.append("]");

        return sb.toString();
    }
}
