package ea.maps;

import ea.data.Color;

public class Region {

    private Color color;
    private boolean isActive;

    public Region(Color color) {
        this.color = color;
        isActive = true;
    }

    public Color getColor() {
        return color;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
