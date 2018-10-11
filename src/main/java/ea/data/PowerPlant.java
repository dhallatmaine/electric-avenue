package ea.data;

import java.util.Set;

public class PowerPlant implements Comparable<PowerPlant> {

    private Integer value;
    private Integer poweredCities;
    private Integer resourceCapacity;
    private Integer type;
    private Set<Resource> resources;

    public Integer getValue() {
        return value;
    }

    public PowerPlant withValue(Integer value) {
        this.value = value;
        return this;
    }

    public Integer getResourceCapacity() {
        return resourceCapacity;
    }

    public PowerPlant withResources(Integer resources) {
        this.resourceCapacity = resources;
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

    public Set<Resource> getResources() {
        return resources;
    }

    public PowerPlant withResourceEnums(Set<Resource> resources) {
        this.resources = resources;
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