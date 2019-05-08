package ea.powerplant;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import ea.resource.Resource;
import ea.game.Game;
import ea.turnorder.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PowerPlantService {

    private ShuffleService shuffleService;

    @Autowired
    public PowerPlantService(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    public List<PowerPlant> createInitialPowerPlants() {
        return ImmutableList.of(
                new PowerPlant()
                        .withValue(3)
                        .withPoweredCities(1)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(4)
                        .withPoweredCities(1)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(5)
                        .withPoweredCities(1)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL, Resource.OIL)),
                new PowerPlant()
                        .withValue(6)
                        .withPoweredCities(1)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(7)
                        .withPoweredCities(2)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(8)
                        .withPoweredCities(2)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(9)
                        .withPoweredCities(1)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(10)
                        .withPoweredCities(2)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(11)
                        .withPoweredCities(2)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(12)
                        .withPoweredCities(2)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL, Resource.OIL)),
                new PowerPlant()
                        .withValue(13)
                        .withPoweredCities(1)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(14)
                        .withPoweredCities(2)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(15)
                        .withPoweredCities(3)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(16)
                        .withPoweredCities(3)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(17)
                        .withPoweredCities(2)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(18)
                        .withPoweredCities(2)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(19)
                        .withPoweredCities(3)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(20)
                        .withPoweredCities(5)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(21)
                        .withPoweredCities(4)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL, Resource.OIL)),
                new PowerPlant()
                        .withValue(22)
                        .withPoweredCities(2)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(23)
                        .withPoweredCities(3)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(24)
                        .withPoweredCities(4)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(25)
                        .withPoweredCities(5)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(26)
                        .withPoweredCities(5)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(27)
                        .withPoweredCities(3)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(28)
                        .withPoweredCities(4)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(29)
                        .withPoweredCities(4)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL, Resource.OIL)),
                new PowerPlant()
                        .withValue(30)
                        .withPoweredCities(6)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(31)
                        .withPoweredCities(6)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(32)
                        .withPoweredCities(6)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(33)
                        .withPoweredCities(4)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(34)
                        .withPoweredCities(5)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(35)
                        .withPoweredCities(5)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(36)
                        .withPoweredCities(7)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(37)
                        .withPoweredCities(4)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(38)
                        .withPoweredCities(7)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.TRASH)),
                new PowerPlant()
                        .withValue(39)
                        .withPoweredCities(6)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.URANIUM)),
                new PowerPlant()
                        .withValue(40)
                        .withPoweredCities(6)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.OIL)),
                new PowerPlant()
                        .withValue(42)
                        .withPoweredCities(6)
                        .withResources(2)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL)),
                new PowerPlant()
                        .withValue(44)
                        .withPoweredCities(5)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)),
                new PowerPlant()
                        .withValue(46)
                        .withPoweredCities(7)
                        .withResources(3)
                        .withResourceEnums(ImmutableSet.of(Resource.COAL, Resource.OIL)),
                new PowerPlant()
                        .withValue(50)
                        .withPoweredCities(6)
                        .withResources(1)
                        .withResourceEnums(ImmutableSet.of(Resource.GREEN)));
    }

    public List<PowerPlant> shuffleDeck(List<PowerPlant> plants, boolean thirteenPlant) {
        List<PowerPlant> shuffled = shuffleService.shuffle(plants);

        if (thirteenPlant) {
            shuffled = shuffled.stream()
                    .filter(plant -> !plant.getValue().equals(13))
                    .collect(Collectors.toList());
            shuffled = Stream.concat(Stream.of(createThirteenPlant()), shuffled.stream())
                    .collect(Collectors.toList());
        }

        // phase 3 card
        return Stream.concat(
                shuffled.stream(),
                Stream.of(new PowerPlant().withValue(0)))
                .collect(Collectors.toList());
    }

    public void flipNewCard(Game game, PowerPlant removedCard) {
        game.getCurrentMarketPlants().remove(removedCard);

        PowerPlant topPlant = game.getDeckPlants().remove(0);
        PowerPlant lowestPlant = game.getFutureMarketPlants().remove(0);
        game.getFutureMarketPlants().add(topPlant);
        game.getCurrentMarketPlants().add(lowestPlant);

        sortPlants(game.getFutureMarketPlants());
        sortPlants(game.getCurrentMarketPlants());
    }

    public List<PowerPlant> setupCurrentMarket(List<PowerPlant> deck) {
        return deck.stream()
                .limit(4)
                .collect(Collectors.toList());
    }

    public List<PowerPlant> setupFutureMarket(List<PowerPlant> deck) {
        return deck.stream()
                .limit(4)
                .collect(Collectors.toList());
    }

    public PowerPlant findPowerPlantInDeckByValue(List<PowerPlant> plants, Integer value) {
        return plants.stream()
                .filter(plant -> plant.getValue().equals(value))
                .findFirst()
                .get();
    }

    private void sortPlants(List<PowerPlant> plants) {
        Collections.sort(plants);
    }

    private PowerPlant createThirteenPlant() {
        return new PowerPlant()
                .withValue(13)
                .withPoweredCities(1)
                .withResources(1)
                .withResourceEnums(ImmutableSet.of(Resource.GREEN));
    }

}