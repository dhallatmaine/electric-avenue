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

    public CityPurchaseResponse withPlayer(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getCost() {
        return cost;
    }

    public CityPurchaseResponse withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<City> getPurchasedCities() {
        return purchasedCities;
    }

    public CityPurchaseResponse withPurchasedCities(List<City> purchasedCities) {
        this.purchasedCities = purchasedCities;
        return this;
    }

    public void setPurchasedCities(List<City> purchasedCities) {
        this.purchasedCities = purchasedCities;
    }
}
