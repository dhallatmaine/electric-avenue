package ea.api;

import ea.data.Color;

public class PassRequest {

    private Color player;

    public Color getPlayer() {
        return player;
    }

    public PassRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }
}
