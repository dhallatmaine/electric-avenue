package ea.player;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ea.player.Player;
import ea.player.PlayerService;
import ea.powerplant.PowerPlant;
import ea.resource.Resource;
import ea.game.Game;
import ea.powerplant.PowerPlantService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class PlayerServiceTest {

    Player player;
    PowerPlantService powerPlantService;
    PlayerService target;
    Game game;

    @Before
    public void setup() {
        player = new Player();
        powerPlantService = mock(PowerPlantService.class);
        target = new PlayerService(powerPlantService);
        game = new Game();
    }

    @Test
    @Parameters({
            " 2 | COAL     |      | COAL | 2 | Maximum 2 capacity coal with 0 coal then can buy 2 ",
            " 2 | COAL;OIL | OIL  | OIL  | 1 | Maximum 2 capacity oil with 1 oil then can buy 1   "
    })
    @TestCaseName("{5}")
    public void getMaxResourcesAllowedForPurchase(
            Integer plantResourcesCapacity,
            String plantResourcesStr,
            String playerResources,
            String typeBeingPurchased,
            Integer expectedPurchaseAmount,
            String description) {

        // Arrange
        PowerPlant plant = new PowerPlant()
                .withValue(1)
                .withResources(plantResourcesCapacity)
                .withResourceEnums(
                        Arrays.stream(plantResourcesStr.split(";"))
                                .map(Resource::valueOf)
                                .collect(Collectors.toSet()));

        player.withPowerPlants(ImmutableList.of(plant));
        Optional.ofNullable(Strings.emptyToNull(playerResources)).ifPresent(playerResource ->
                player.withResources(ImmutableMap.of(
                        plant.getValue(), ImmutableList.of(Resource.valueOf(playerResources)))));

        // Act
        int actual = target.getMaxResourcesAllowedForPurchase(player, Resource.valueOf(typeBeingPurchased));

        // Assert
        assertThat(actual).isEqualTo(expectedPurchaseAmount);
    }

    @Test
    public void addToPlayerResources() {
        // Arrange
        PowerPlant plant = new PowerPlant()
                .withValue(1)
                .withResources(2)
                .withResourceEnums(ImmutableSet.of(Resource.COAL));

        player.withPowerPlants(ImmutableList.of(plant));
        player.withResources(ImmutableMap.of(
                plant.getValue(), new ArrayList<>()));

        // Act
        target.addToPlayerResources(player, Resource.COAL, 2);

        // Assert
        assertThat(player.getResources().get(plant.getValue()).size()).isEqualTo(2);
    }

    @Test
    @Parameters({
            " 1;2   | 5 |   | 1;2;5 | Add when player not violating max plant capacity ",
            " 1;2;3 | 5 | 1 | 2;3;5 | Add when player violating max plant capacity     "
    })
    @TestCaseName("{4}")
    public void capturePlant(
            String playerPlantValuesStr,
            String plantValueToAdd,
            String plantValueToRemove,
            String expectedPlants,
            String description) {
        // Arrange
        PowerPlant plant = new PowerPlant()
                .withValue(Integer.valueOf(plantValueToAdd));

        List<PowerPlant> playerPlants = Arrays.stream(playerPlantValuesStr.split(";"))
                .filter(str -> !str.isEmpty())
                .map(value -> new PowerPlant().withValue(Integer.valueOf(value)))
                .collect(Collectors.toList());

        List<PowerPlant> expected = Arrays.stream(expectedPlants.split(";"))
                .map(value -> new PowerPlant().withValue(Integer.valueOf(value)))
                .collect(Collectors.toList());

        // Act
        Player actual = target.capturePlant(
                game,
                plant,
                player.withPowerPlants(playerPlants).withMoney(50),
                Integer.valueOf(plantValueToAdd),
                Optional.ofNullable(Strings.emptyToNull(plantValueToRemove))
                        .map(value -> new PowerPlant().withValue(Integer.valueOf(value))));

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new Player()
                        .withPowerPlants(expected)
                        .withMoney(45));
    }

}
