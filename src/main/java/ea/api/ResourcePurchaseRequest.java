package ea.api;

import ea.player.Color;
import ea.resource.Resource;

import java.util.Map;

public class ResourcePurchaseRequest {

    private Color player;
    private Map<Resource, Integer> resources;

    public Color getPlayer() {
        return player;
    }

    public ResourcePurchaseRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public Map<Resource, Integer> getResources() {
        return resources;
    }

    public ResourcePurchaseRequest withResources(Map<Resource, Integer> resources) {
        this.resources = resources;
        return this;
    }
}
