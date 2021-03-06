package ea.game;

import ea.player.Color;
import ea.powerplant.PowerPlant;

import java.util.List;

public class AuctionRound {

    private List<Color> auctionOrder;
    private PowerPlant plant;
    private Color highBidder;
    private Integer bid;
    private boolean auctionFinished = false;

    public List<Color> getAuctionOrder() {
        return auctionOrder;
    }

    public AuctionRound withAuctionOrder(List<Color> auctionOrder) {
        this.auctionOrder = auctionOrder;
        return this;
    }

    public PowerPlant getPlant() {
        return plant;
    }

    public AuctionRound withPlant(PowerPlant plant) {
        this.plant = plant;
        return this;
    }

    public Color getHighBidder() {
        return highBidder;
    }

    public AuctionRound withHighBidder(Color highBidder) {
        this.highBidder = highBidder;
        return this;
    }

    public Integer getBid() {
        return bid;
    }

    public AuctionRound withBid(Integer bid) {
        this.bid = bid;
        return this;
    }

    public boolean getAuctionFinished() {
        return auctionFinished;
    }

    public AuctionRound withAuctionFinished(boolean auctionFinished) {
        this.auctionFinished = auctionFinished;
        return this;
    }

}
