package ea.data;

public class BidRequest {

    private Color player;
    private Integer plantValue;
    private Integer bidAmount;

    public Color getPlayer() {
        return player;
    }

    public BidRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public Integer getPlantValue() {
        return plantValue;
    }

    public BidRequest withPlantValue(Integer plantValue) {
        this.plantValue = plantValue;
        return this;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public BidRequest withBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
        return this;
    }

}
