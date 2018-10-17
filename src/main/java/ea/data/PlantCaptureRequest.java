package ea.data;

public class PlantCaptureRequest {

    private Color player;
    private Integer plant;
    private Integer plantToRemove;

    public Color getPlayer() {
        return player;
    }

    public PlantCaptureRequest withPlayer(Color player) {
        this.player = player;
        return this;
    }

    public Integer getPlant() {
        return plant;
    }

    public PlantCaptureRequest withPlant(Integer plant) {
        this.plant = plant;
        return this;
    }

    public Integer getPlantToRemove() {
        return plantToRemove;
    }

    public PlantCaptureRequest withPlantToRemove(Integer plantToRemove) {
        this.plantToRemove = plantToRemove;
        return this;
    }
}
