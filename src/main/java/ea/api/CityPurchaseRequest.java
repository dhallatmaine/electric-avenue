package ea.api;

import ea.player.Color;

import java.util.List;

public class CityPurchaseRequest {

    private Color player;
    private List<List<String>> cityPaths;

    public Color getPlayer() {
        return player;
    }

    public void setPlayer(Color player) {
        this.player = player;
    }

    public CityPurchaseRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public List<List<String>> getCityPaths() {
        return cityPaths;
    }

    public void setCityPaths(List<List<String>> cityPaths) {
        this.cityPaths = cityPaths;
    }

    public CityPurchaseRequest withCityPaths(List<List<String>> cityPaths) {
        this.cityPaths = cityPaths;
        return this;
    }
}
