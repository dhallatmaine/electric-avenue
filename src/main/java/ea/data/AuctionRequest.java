package ea.data;

public class AuctionRequest {

    private Integer bidAmount;
    private Integer plant;
    private Color player;

    public Integer getBidAmount() {
        return bidAmount;
    }

    public AuctionRequest withBid(Integer bid) {
        this.bidAmount = bid;
        return this;
    }

    public Integer getPlant() {
        return plant;
    }

    public AuctionRequest withPlant(Integer plant) {
        this.plant = plant;
        return this;
    }

    public Color getPlayer() {
        return player;
    }

    public AuctionRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }
}
