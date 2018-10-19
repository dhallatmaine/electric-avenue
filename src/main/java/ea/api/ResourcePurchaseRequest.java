package ea.api;

import ea.data.Color;
import ea.data.Resource;

import java.util.List;

public class ResourcePurchaseRequest {

    private Color player;
    private List<Resource> resources;

    public Color getPlayer() {
        return player;
    }

    public ResourcePurchaseRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public ResourcePurchaseRequest withResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }
}
