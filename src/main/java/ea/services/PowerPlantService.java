package ea.services;

import ea.data.PowerPlant;
import ea.data.Resource;
import ea.engine.GameState;
import ea.engine.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PowerPlantService {

  private final GameState gameState;

  @Autowired
  public PowerPlantService(GameState gameState) {
    this.gameState = gameState;
  }

  private List<PowerPlant> discardPlants;

  public List<PowerPlant> getDiscardPlants() { return discardPlants; }
  public void setDiscardPlants(List<PowerPlant> discardPlants) { this.discardPlants = discardPlants; }

  public List<PowerPlant> createInitialPowerPlants() {
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    plants.add(new PowerPlant()
            .withValue(3)
            .withPoweredCities(1)
            .withType(Resource.OIL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(4)
            .withPoweredCities(1)
            .withType(Resource.COAL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(5)
            .withPoweredCities(1)
            .withType(Resource.HYBRID)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(6)
            .withPoweredCities(1)
            .withType(Resource.TRASH)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(7)
            .withPoweredCities(2)
            .withType(Resource.OIL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(8)
            .withPoweredCities(2)
            .withType(Resource.COAL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(9)
            .withPoweredCities(1)
            .withType(Resource.OIL)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(10)
            .withPoweredCities(2)
            .withType(Resource.COAL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(11)
            .withPoweredCities(2)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(12)
            .withPoweredCities(2)
            .withType(Resource.HYBRID)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(13)
            .withPoweredCities(1)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(14)
            .withPoweredCities(2)
            .withType(Resource.TRASH)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(15)
            .withPoweredCities(3)
            .withType(Resource.COAL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(16)
            .withPoweredCities(3)
            .withType(Resource.OIL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(17)
            .withPoweredCities(2)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(18)
            .withPoweredCities(2)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(19)
            .withPoweredCities(3)
            .withType(Resource.TRASH)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(20)
            .withPoweredCities(5)
            .withType(Resource.COAL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(21)
            .withPoweredCities(4)
            .withType(Resource.HYBRID)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(22)
            .withPoweredCities(2)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(23)
            .withPoweredCities(3)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(24)
            .withPoweredCities(4)
            .withType(Resource.TRASH)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(25)
            .withPoweredCities(5)
            .withType(Resource.COAL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(26)
            .withPoweredCities(5)
            .withType(Resource.OIL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(27)
            .withPoweredCities(3)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(28)
            .withPoweredCities(4)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(29)
            .withPoweredCities(4)
            .withType(Resource.HYBRID)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(30)
            .withPoweredCities(6)
            .withType(Resource.TRASH)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(31)
            .withPoweredCities(6)
            .withType(Resource.COAL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(32)
            .withPoweredCities(6)
            .withType(Resource.OIL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(33)
            .withPoweredCities(4)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(34)
            .withPoweredCities(5)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(35)
            .withPoweredCities(5)
            .withType(Resource.OIL)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(36)
            .withPoweredCities(7)
            .withType(Resource.COAL)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(37)
            .withPoweredCities(4)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(38)
            .withPoweredCities(7)
            .withType(Resource.TRASH)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(39)
            .withPoweredCities(6)
            .withType(Resource.URANIUM)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(40)
            .withPoweredCities(6)
            .withType(Resource.OIL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(42)
            .withPoweredCities(6)
            .withType(Resource.COAL)
            .withResources(2));

    plants.add(new PowerPlant()
            .withValue(44)
            .withPoweredCities(5)
            .withType(Resource.GREEN)
            .withResources(1));

    plants.add(new PowerPlant()
            .withValue(46)
            .withPoweredCities(7)
            .withType(Resource.HYBRID)
            .withResources(3));

    plants.add(new PowerPlant()
            .withValue(50)
            .withPoweredCities(6)
            .withType(Resource.GREEN)
            .withResources(1));

    return plants;
  }

  public void setupMarket(Integer gameId) {
    setupCurrentMarket(gameId);
    setupFutureMarket(gameId);
  }

  public List<PowerPlant> shuffleDeck(List<PowerPlant> plants, boolean thirteenPlant) {
    List<PowerPlant> shuffled = new ArrayList<>(plants);
    Collections.shuffle(shuffled);

    // not all maps place 13 on the top of the shuffled deck
    if (thirteenPlant) {
      PowerPlant thirteenPowerPlant = getPowerPlantInDeckByValue(shuffled, 13);
      shuffled.remove(thirteenPowerPlant);
      shuffled.add(0, thirteenPowerPlant);
    }

    // phase 3 card
    shuffled.add(new PowerPlant().withValue(0));

    return shuffled;
  }

  public void flipNewCard(Integer gameId, PowerPlant removedCard) {
    State game = gameState.getById(gameId);

    game.getCurrentMarketPlants().remove(removedCard);

    PowerPlant topPlant = game.getDeckPlants().remove(0);

    game.getFutureMarketPlants().add(topPlant);

    sortPlants(game.getFutureMarketPlants());

    PowerPlant lowestPlant = game.getFutureMarketPlants().remove(0);

    game.getCurrentMarketPlants().add(lowestPlant);

    sortPlants(game.getCurrentMarketPlants());
  }

  private void sortPlants(List<PowerPlant> plants) {
    Collections.sort(plants);
  }

  private PowerPlant getPowerPlantInDeckByValue(List<PowerPlant> plants, Integer value) {
    Map<Integer, PowerPlant> valueToPlant = plants.stream()
            .collect(Collectors.toMap(PowerPlant::getValue, Function.identity()));

    return Optional.ofNullable(
            valueToPlant.get(value))
            .orElse(null);
  }

  private void setupCurrentMarket(Integer gameId) {
    State game = gameState.getById(gameId);
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    int count = 1;
    Iterator itr = game.getDeckPlants().iterator();
    while (itr.hasNext() && count <= 4) {
      PowerPlant p = (PowerPlant) itr.next();
      plants.add(p);
      count++;
    }

    game.setCurrentMarketPlants(plants);
    game.getDeckPlants().removeAll(plants);
  }

  private void setupFutureMarket(Integer gameId) {
    State game = gameState.getById(gameId);
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    int count = 1;
    Iterator itr = game.getDeckPlants().iterator();
    while (itr.hasNext() && count <= 4) {
      PowerPlant p = (PowerPlant) itr.next();
      plants.add(p);
      count++;
    }

    game.setFutureMarketPlants(plants);
    game.getDeckPlants().removeAll(plants);
  }

}