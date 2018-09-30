package ea.data;

public class PowerPlant implements Comparable<PowerPlant> {

    private Integer value;
    private Integer poweredCities;
    private Integer resources;
    private Integer type;

    public Integer getValue() {
        return value;
    }

    public PowerPlant withValue(Integer value) {
        this.value = value;
        return this;
    }

    public Integer getResources() {
        return resources;
    }

    public PowerPlant withResources(Integer resources) {
        this.resources = resources;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public PowerPlant withType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getPoweredCities() {
        return poweredCities;
    }

    public PowerPlant withPoweredCities(Integer poweredCities) {
        this.poweredCities = poweredCities;
        return this;
    }

    public int compareTo(PowerPlant plant) {
        return getValue().compareTo(plant.getValue());
    }

    @Override
    public boolean equals(Object o) {
        PowerPlant other = (PowerPlant) o;
        return this.value.equals(other.getValue());
    }

}