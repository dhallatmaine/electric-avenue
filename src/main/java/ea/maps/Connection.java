package ea.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Connection {

    private String cityName;
    private int cost;

    public Connection() {
    }

    public Connection(String cityName, int cost) {
        this.cityName = cityName;
        this.cost = cost;
    }

    public String getCityName() {
        return cityName;
    }

    @JsonProperty("city")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[" + cityName + "," + cost + "]";
    }
}
