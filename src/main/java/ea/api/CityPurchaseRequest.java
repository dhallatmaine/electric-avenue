package ea.api;

import ea.data.Color;

public class CityPurchaseRequest {

    private Color player;
    private String cityName;

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

    public String getCityName() {
        return cityName;
    }

    public CityPurchaseRequest withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
