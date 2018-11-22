package ea.api;

import ea.data.Color;

import java.util.List;

public class CityPurchaseRequest {

    private Color player;
    private List<List<String>> cityPaths;

    public Color getPlayer() {
        return player;
    }

    public CityPurchaseRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Color player) {
        this.player = player;
    }

    public List<List<String>> getCityPaths() {
        return cityPaths;
    }

    public CityPurchaseRequest withCityPaths(List<List<String>> cityPaths) {
        this.cityPaths = cityPaths;
        return this;
    }

    public void setCityPaths(List<List<String>> cityPaths) {
        this.cityPaths = cityPaths;
    }
}
