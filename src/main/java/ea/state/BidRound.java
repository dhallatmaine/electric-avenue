package ea.state;

import ea.data.Color;

import java.util.List;

public class BidRound {

    private List<Color> bidOrder;
    private boolean plantPurchased;
    private Integer round;
    private List<AuctionRound> auctionRounds;

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

    public Integer getRound() {
        return round;
    }

    public BidRound withRound(Integer round) {
        this.round = round;
        return this;
    }

    public List<AuctionRound> getAuctionRounds() {
        return auctionRounds;
    }

    public BidRound withAuctionRounds(List<AuctionRound> auctionRounds) {
        this.auctionRounds = auctionRounds;
        return this;
    }
}
