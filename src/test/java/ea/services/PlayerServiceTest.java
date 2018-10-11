package ea.services;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.ResourceEnum;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PlayerServiceTest {

    private Player player;
    private PlayerService target;

    @Before
    public void setup() {
        player = new Player();
        target = new PlayerService();
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
                .withResources(plantResourcesCapacity)
                .withResourceEnums(
                        Arrays.stream(plantResourcesStr.split(";"))
                                .map(ResourceEnum::valueOf)
                                .collect(Collectors.toSet()));

        player.setPowerPlants(ImmutableList.of(plant));
        Optional.ofNullable(Strings.emptyToNull(playerResources)).ifPresent(playerResource ->
                player.setResources(ImmutableMap.of(
                        plant, ImmutableList.of(ResourceEnum.valueOf(playerResources)))));

        // Act
        int actual = target.getMaxResourcesAllowedForPurchase(player, ResourceEnum.valueOf(typeBeingPurchased));

        // Assert
        assertThat(actual).isEqualTo(expectedPurchaseAmount);
    }

    @Test
    public void addToPlayerResources() {
        // Arrange
        PowerPlant plant = new PowerPlant()
                .withValue(1)
                .withResources(2)
                .withResourceEnums(ImmutableSet.of(ResourceEnum.COAL));

        player.setPowerPlants(ImmutableList.of(plant));
        player.setResources(ImmutableMap.of(plant, new ArrayList<>()));

        // Act
        target.addToPlayerResources(player, ResourceEnum.COAL, 2);

        // Assert
        assertThat(player.getResources().get(plant).size()).isEqualTo(2);
    }

}
