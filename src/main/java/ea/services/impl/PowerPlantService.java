package ea.services.impl;

import ea.data.PowerPlant;
import ea.data.Resource;
import ea.engine.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

  public void setupMarket() {
    setupCurrentMarket();
    setupFutureMarket();
  }

  public void shuffleDeck(boolean thirteenPlant) {
    Collections.shuffle(gameState.getDeckPlants());

    // special case for 13 plant
    if (thirteenPlant) {
      PowerPlant thirteenPowerPlant = getPowerPlantInDeckByValue(gameState.getDeckPlants(), 13);
      gameState.getDeckPlants().remove(thirteenPowerPlant);
      gameState.getDeckPlants().add(0, thirteenPowerPlant);
    }

    gameState.getDeckPlants().add(new PowerPlant().withValue(0));
  }

  public void flipNewCard(PowerPlant removedCard) {
    gameState.getCurrentMarketPlants().remove(removedCard);

    PowerPlant topPlant = gameState.getDeckPlants().remove(0);

    gameState.getFutureMarketPlants().add(topPlant);

    sortPlants(gameState.getFutureMarketPlants());

    PowerPlant lowestPlant = gameState.getFutureMarketPlants().remove(0);

    gameState.getCurrentMarketPlants().add(lowestPlant);

    sortPlants(gameState.getCurrentMarketPlants());
  }

  private void sortPlants(List<PowerPlant> plants) {
    Collections.sort(plants);
  }

  private PowerPlant getPowerPlantInDeckByValue(List<PowerPlant> plants, Integer value) {
    Iterator itr = plants.iterator();
    while (itr.hasNext()) {
      PowerPlant p = (PowerPlant) itr.next();
      if (p.getValue().equals(value)) {
        return p;
      }
    }

    return null;
  }

  private void setupCurrentMarket() {
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    int count = 1;
    Iterator itr = gameState.getDeckPlants().iterator();
    while (itr.hasNext() && count <= 4) {
      PowerPlant p = (PowerPlant) itr.next();
      plants.add(p);
      count++;
    }

    gameState.setCurrentMarketPlants(plants);
    gameState.getDeckPlants().removeAll(plants);
  }

  private void setupFutureMarket() {
    List<PowerPlant> plants = new LinkedList<PowerPlant>();

    int count = 1;
    Iterator itr = gameState.getDeckPlants().iterator();
    while (itr.hasNext() && count <= 4) {
      PowerPlant p = (PowerPlant) itr.next();
      plants.add(p);
      count++;
    }

    gameState.setFutureMarketPlants(plants);
    gameState.getDeckPlants().removeAll(plants);
  }

}