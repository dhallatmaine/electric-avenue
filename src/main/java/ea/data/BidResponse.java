package ea.data;

public class BidResponse {

    private BidRequest bid;
    private Color nextPlayer;
    private boolean biddingEnded;

    public BidRequest getBid() {
        return bid;
    }

    public BidResponse withBid(BidRequest bid) {
        this.bid = bid;
        return this;
    }

    public Color getNextPlayer() {
        return nextPlayer;
    }

    public BidResponse withNextPlayer(Color nextPlayer) {
        this.nextPlayer = nextPlayer;
        return this;
    }

    public boolean isBiddingEnded() {
        return biddingEnded;
    }

    public BidResponse withBiddingEnded(boolean biddingEnded) {
        this.biddingEnded = biddingEnded;
        return this;
    }
}
