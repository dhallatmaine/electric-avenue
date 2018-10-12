package ea.data;

public class BidRequest {

    private Color player;
    private Integer plantValue;
    private Integer plantToRemoveValue;
    private Integer bidAmount;

    public Color getPlayer() {
        return player;
    }

    public void setPlayer(Color player) {
        this.player = player;
    }

    public Integer getPlantValue() {
        return plantValue;
    }

    public void setPlantValue(Integer plantValue) {
        this.plantValue = plantValue;
    }

    public Integer getPlantToRemoveValue() {
        return plantToRemoveValue;
    }

    public void setPlantToRemoveValue(Integer plantToRemoveValue) {
        this.plantToRemoveValue = plantToRemoveValue;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }
}
