package ea.data;

public class AuctionRequest {

    private Integer bidAmount;
    private Integer plantValue;
    private Integer plantValueToRemove;
    private Color player;

    public Integer getBidAmount() {
        return bidAmount;
    }

    public AuctionRequest withBid(Integer bid) {
        this.bidAmount = bid;
        return this;
    }

    public Integer getPlantValue() {
        return plantValue;
    }

    public AuctionRequest withplantValue(Integer plantValue) {
        this.plantValue = plantValue;
        return this;
    }

    public Integer getplantValueToRemove() {
        return plantValueToRemove;
    }

    public AuctionRequest withplantValueToRemove(Integer plantValueToRemove) {
        this.plantValueToRemove = plantValueToRemove;
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
