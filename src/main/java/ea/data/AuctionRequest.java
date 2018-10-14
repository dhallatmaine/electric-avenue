package ea.data;

public class AuctionRequest {

    private boolean pass;
    private Integer bid;
    private Color player;

    public boolean isPass() {
        return pass;
    }

    public AuctionRequest withPass(boolean pass) {
        this.pass = pass;
        return this;
    }

    public Integer getBid() {
        return bid;
    }

    public AuctionRequest withBid(Integer bid) {
        this.bid = bid;
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
