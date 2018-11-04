package ea.api;

import ea.data.Color;
import ea.data.Resource;

import java.util.List;
import java.util.Map;

public class ResourcePlaceRequest {

    private Color player;
    // plant id -> (resources -> amount)
    private Map<Integer, List<Resource>> resourcesToPlace;

    public Color getPlayer() {
        return player;
    }

    public ResourcePlaceRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public Map<Integer, List<Resource>> getResourcesToPlace() {
        return resourcesToPlace;
    }

    public ResourcePlaceRequest withResourcesToPlace(Map<Integer, List<Resource>> resourcesToPlace) {
        this.resourcesToPlace = resourcesToPlace;
        return this;
    }
}
