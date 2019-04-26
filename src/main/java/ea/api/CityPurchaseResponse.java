package ea.api;

import ea.data.Player;
import ea.maps.City;

import java.util.List;

public class CityPurchaseResponse {

    private Player player;
    private Integer cost;
    private List<City> purchasedCities;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CityPurchaseResponse withPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public CityPurchaseResponse withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public List<City> getPurchasedCities() {
        return purchasedCities;
    }

    public void setPurchasedCities(List<City> purchasedCities) {
        this.purchasedCities = purchasedCities;
    }

    public CityPurchaseResponse withPurchasedCities(List<City> purchasedCities) {
        this.purchasedCities = purchasedCities;
        return this;
    }
}
