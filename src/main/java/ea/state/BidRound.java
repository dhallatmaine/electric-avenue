package ea.state;

import ea.data.Color;
import ea.data.PowerPlant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidRound {

    private List<Color> bidOrder;
    private boolean plantPurchased = false;
    private Map<PowerPlant, AuctionRound> auctionRounds = new HashMap<>();

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

    public Map<PowerPlant, AuctionRound> getAuctionRounds() {
        return auctionRounds;
    }

    public BidRound withAuctionRounds(Map<PowerPlant, AuctionRound> auctionRounds) {
        this.auctionRounds = auctionRounds;
        return this;
    }
}
