package ea.services;

import com.google.common.collect.ImmutableSet;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.data.ResourceEnum;
import ea.engine.State;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PowerPlantService {

  private List<PowerPlant> discardPlants;

  public List<PowerPlant> getDiscardPlants() { return discardPlants; }
  public void setDiscardPlants(List<PowerPlant> discardPlants) { this.discardPlants = discardPlants; }

  public List<PowerPlant> createInitialPowerPlants() {
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    plants.add(new PowerPlant()
            .withValue(3)
            .withPoweredCities(1)
            .withType(Resource.OIL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(4)
            .withPoweredCities(1)
            .withType(Resource.COAL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(5)
            .withPoweredCities(1)
            .withType(Resource.HYBRID)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL, ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(6)
            .withPoweredCities(1)
            .withType(Resource.TRASH)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(7)
            .withPoweredCities(2)
            .withType(Resource.OIL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(8)
            .withPoweredCities(2)
            .withType(Resource.COAL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(9)
            .withPoweredCities(1)
            .withType(Resource.OIL)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(10)
            .withPoweredCities(2)
            .withType(Resource.COAL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(11)
            .withPoweredCities(2)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(12)
            .withPoweredCities(2)
            .withType(Resource.HYBRID)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL, ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(13)
            .withPoweredCities(1)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(14)
            .withPoweredCities(2)
            .withType(Resource.TRASH)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(15)
            .withPoweredCities(3)
            .withType(Resource.COAL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(16)
            .withPoweredCities(3)
            .withType(Resource.OIL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(17)
            .withPoweredCities(2)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(18)
            .withPoweredCities(2)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(19)
            .withPoweredCities(3)
            .withType(Resource.TRASH)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(20)
            .withPoweredCities(5)
            .withType(Resource.COAL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(21)
            .withPoweredCities(4)
            .withType(Resource.HYBRID)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL, ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(22)
            .withPoweredCities(2)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(23)
            .withPoweredCities(3)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(24)
            .withPoweredCities(4)
            .withType(Resource.TRASH)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(25)
            .withPoweredCities(5)
            .withType(Resource.COAL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(26)
            .withPoweredCities(5)
            .withType(Resource.OIL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(27)
            .withPoweredCities(3)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(28)
            .withPoweredCities(4)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(29)
            .withPoweredCities(4)
            .withType(Resource.HYBRID)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL, ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(30)
            .withPoweredCities(6)
            .withType(Resource.TRASH)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(31)
            .withPoweredCities(6)
            .withType(Resource.COAL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(32)
            .withPoweredCities(6)
            .withType(Resource.OIL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(33)
            .withPoweredCities(4)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(34)
            .withPoweredCities(5)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(35)
            .withPoweredCities(5)
            .withType(Resource.OIL)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(36)
            .withPoweredCities(7)
            .withType(Resource.COAL)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(37)
            .withPoweredCities(4)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(38)
            .withPoweredCities(7)
            .withType(Resource.TRASH)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.TRASH)));

    plants.add(new PowerPlant()
            .withValue(39)
            .withPoweredCities(6)
            .withType(Resource.URANIUM)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.URANIUM)));

    plants.add(new PowerPlant()
            .withValue(40)
            .withPoweredCities(6)
            .withType(Resource.OIL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(42)
            .withPoweredCities(6)
            .withType(Resource.COAL)
            .withResources(2)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL)));

    plants.add(new PowerPlant()
            .withValue(44)
            .withPoweredCities(5)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    plants.add(new PowerPlant()
            .withValue(46)
            .withPoweredCities(7)
            .withType(Resource.HYBRID)
            .withResources(3)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL, ResourceEnum.OIL)));

    plants.add(new PowerPlant()
            .withValue(50)
            .withPoweredCities(6)
            .withType(Resource.GREEN)
            .withResources(1)
            .withResourceEnums(ImmutableSet.of(ResourceEnum.GREEN)));

    return plants;
  }

  public List<PowerPlant> shuffleDeck(List<PowerPlant> plants, boolean thirteenPlant) {
    List<PowerPlant> shuffled = new ArrayList<>(plants);
    Collections.shuffle(shuffled);

    // not all maps place 13 on the top of the shuffled deck
    if (thirteenPlant) {
      Optional<PowerPlant> thirteenPowerPlant = getPowerPlantInDeckByValue(shuffled, 13);
      thirteenPowerPlant.ifPresent(thirteen -> {
        shuffled.remove(thirteen);
        shuffled.add(0, thirteen);
      });
    }

    // phase 3 card
    shuffled.add(new PowerPlant().withValue(0));

    return shuffled;
  }

  public void flipNewCard(State game, PowerPlant removedCard) {
    game.getCurrentMarketPlants().remove(removedCard);

    PowerPlant topPlant = game.getDeckPlants().remove(0);
    PowerPlant lowestPlant = game.getFutureMarketPlants().remove(0);
    game.getFutureMarketPlants().add(topPlant);
    game.getCurrentMarketPlants().add(lowestPlant);

    sortPlants(game.getFutureMarketPlants());
    sortPlants(game.getCurrentMarketPlants());
  }

  public void setupCurrentMarket(State game) {
    List<PowerPlant> plants = game.getDeckPlants().stream()
            .limit(4)
            .collect(Collectors.toList());

    game.withCurrentMarketPlants(plants);
    game.getDeckPlants().removeAll(plants);
  }

  public void setupFutureMarket(State game) {
    List<PowerPlant> plants = game.getDeckPlants().stream()
            .limit(4)
            .collect(Collectors.toList());

    game.withFutureMarketPlants(plants);
    game.getDeckPlants().removeAll(plants);
  }

  private void sortPlants(List<PowerPlant> plants) {
    Collections.sort(plants);
  }

  private Optional<PowerPlant> getPowerPlantInDeckByValue(List<PowerPlant> plants, Integer value) {
    return plants.stream()
            .filter(plant -> plant.getValue().equals(value))
            .findFirst();
  }

}