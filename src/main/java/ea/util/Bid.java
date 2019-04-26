package ea.util;

import ea.data.Player;
import ea.data.PowerPlant;

public class Bid {

    private Player player;
    private Integer bid;
    private PowerPlant powerPlant;

    public Bid(Player player, Integer bid, PowerPlant powerPlant) {
        this.player = player;
        this.bid = bid;
        this.powerPlant = powerPlant;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public PowerPlant getPowerPlant() {
        return powerPlant;
    }

    public void setPowerPlant(PowerPlant powerPlant) {
        this.powerPlant = powerPlant;
    }

}