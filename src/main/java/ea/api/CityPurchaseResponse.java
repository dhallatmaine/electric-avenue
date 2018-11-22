package ea.api;

import ea.data.Player;
import ea.maps.City;

public class CityPurchaseResponse {

    private Player player;
    private City city;
    private Integer cost;

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

    public City getCity() {
        return city;
    }

    public CityPurchaseResponse withCity(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
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
}
