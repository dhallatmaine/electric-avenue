package ea.data;

public class BidResponse {

    private boolean auctionStarted;
    private PowerPlant plant;

    public boolean getAuctionStarted() {
        return auctionStarted;
    }

    public BidResponse withAuctionStarted(boolean auctionStarted) {
        this.auctionStarted = auctionStarted;
        return this;
    }

    public PowerPlant getPlant() {
        return plant;
    }

    public BidResponse withPlant(PowerPlant plant) {
        this.plant = plant;
        return this;
    }
}
