package ea.maps;

import ea.data.Color;

public class District {

    private Integer cost;
    private Color owner;

    public Integer getCost() {
        return cost;
    }

    public District withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public Color getOwner() {
        return owner;
    }

    public District withOwner(Color owner) {
        this.owner = owner;
        return this;
    }
}
