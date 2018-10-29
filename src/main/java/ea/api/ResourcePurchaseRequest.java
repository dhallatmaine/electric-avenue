package ea.api;

import ea.data.Color;
import ea.data.Resource;

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
