package ea.data;

public class AuctionResponse {

    private PowerPlant plant;
    private Color highBidder;
    private Color nextBidder;
    private Integer highBid;
    private boolean auctionFinished = false;

    public PowerPlant getPlant() {
        return plant;
    }

    public AuctionResponse withPlant(PowerPlant plant) {
        this.plant = plant;
        return this;
    }

    public Color getHighBidder() {
        return highBidder;
    }

    public AuctionResponse withHighBidder(Color highBidder) {
        this.highBidder = highBidder;
        return this;
    }

    public Color getNextBidder() {
        return nextBidder;
    }

    public AuctionResponse withNextBidder(Color nextBidder) {
        this.nextBidder = nextBidder;
        return this;
    }

    public Integer getHighBid() {
        return highBid;
    }

    public AuctionResponse withHighBid(Integer highBid) {
        this.highBid = highBid;
        return this;
    }

    public boolean getAuctionFinished() {
        return auctionFinished;
    }

    public AuctionResponse withAuctionFinished(boolean auctionFinished) {
        this.auctionFinished = auctionFinished;
        return this;
    }
}
