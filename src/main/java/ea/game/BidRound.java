package ea.game;

import ea.player.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidRound {

    private List<Color> bidOrder;
    private boolean plantPurchased = false;
    private Map<Integer, AuctionRound> auctionRounds = new HashMap<>();

    public List<Color> getBidOrder() {
        return bidOrder;
    }

    public BidRound withBidOrder(List<Color> bidOrder) {
        this.bidOrder = bidOrder;
        return this;
    }

    public boolean getPlantPurchased() {
        return plantPurchased;
    }

    public BidRound withPlantPurchased(boolean plantPurchased) {
        this.plantPurchased = plantPurchased;
        return this;
    }

    public Map<Integer, AuctionRound> getAuctionRounds() {
        return auctionRounds;
    }

    public BidRound withAuctionRounds(Map<Integer, AuctionRound> auctionRounds) {
        this.auctionRounds = auctionRounds;
        return this;
    }
}
