package ea.api;

import ea.player.Color;

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
