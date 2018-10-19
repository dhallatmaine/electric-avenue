package ea.api;

import ea.data.Player;
import ea.data.PowerPlant;

public class PlantCaptureResponse {

    private Player player;
    private PowerPlant plant;
    private PowerPlant plantRemoved;

    public Player getPlayer() {
        return player;
    }

    public PlantCaptureResponse withPlayer(Player player) {
        this.player = player;
        return this;
    }

    public PowerPlant getPlant() {
        return plant;
    }

    public PlantCaptureResponse withPlant(PowerPlant plant) {
        this.plant = plant;
        return this;
    }

    public PowerPlant getPlantRemoved() {
        return plantRemoved;
    }

    public PlantCaptureResponse withPlantRemoved(PowerPlant plantRemoved) {
        this.plantRemoved = plantRemoved;
        return this;
    }
}
