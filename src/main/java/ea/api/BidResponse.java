package ea.api;

import ea.player.Color;
import ea.powerplant.PowerPlant;

import java.util.List;

public class BidResponse {

    private PowerPlant plant;
    private boolean auctionStarted = false;
    private boolean phaseOver = false;
    private Color playerToStartAuction;
    private List<Color> order;

    public PowerPlant getPlant() {
        return plant;
    }

    public BidResponse withPlant(PowerPlant plant) {
        this.plant = plant;
        return this;
    }

    public boolean getAuctionStarted() {
        return auctionStarted;
    }

    public BidResponse withAuctionStarted(boolean auctionStarted) {
        this.auctionStarted = auctionStarted;
        return this;
    }

    public boolean getPhaseOver() {
        return phaseOver;
    }

    public BidResponse withPhaseOver(boolean phaseOver) {
        this.phaseOver = phaseOver;
        return this;
    }

    public Color getPlayerToStartAuction() {
        return playerToStartAuction;
    }

    public BidResponse withPlayerToStartAuction(Color playerToStartAuction) {
        this.playerToStartAuction = playerToStartAuction;
        return this;
    }

    public List<Color> getOrder() {
        return order;
    }

    public BidResponse withOrder(List<Color> order) {
        this.order = order;
        return this;
    }
}
