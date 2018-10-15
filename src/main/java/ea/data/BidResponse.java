package ea.data;

public class BidResponse {

    private boolean auctionStarted = false;
    private PowerPlant plant;
    private boolean phaseOver = false;

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

    public boolean getPhaseOver() {
        return phaseOver;
    }

    public BidResponse withPhaseOver(boolean phaseOver) {
        this.phaseOver = phaseOver;
        return this;
    }
}
