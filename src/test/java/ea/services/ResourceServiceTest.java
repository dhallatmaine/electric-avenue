package ea.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ea.api.ResourcePlaceRequest;
import ea.api.ResourcePurchaseRequest;
import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.state.State;
import ea.maps.America;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class ResourceServiceTest {

    PlayerService playerService;
    GameService gameService;
    ResourceService target;

    State game;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        gameService = mock(GameService.class);
        target = new ResourceService(playerService, gameService);
        game = new State().withResources(America.initializeResources());
    }

    @Test
    @Parameters({
            " COAL    | 4 | 5  | Four coal for 5 elektro    ",
            " OIL     | 4 | 13 | Four oil for 13 elektro    ",
            " TRASH   | 4 | 29 | Four trash for 29 elektro  ",
            " URANIUM | 2 | 30 | Two Uranium for 30 elektro "
    })
    @TestCaseName("{3}")
    public void getPrice(
            String type,
            Integer amount,
            Integer expectedPrice,
            String description) {

        // Arrange
        Resource resourceType = Resource.valueOf(type);

        // Act
        Integer actual = target.getPrice(game.getResources().get(resourceType), amount);

        // Assert
        assertThat(actual).isEqualTo(expectedPrice);
    }

    @Test
    @Parameters({
            " COAL    | 4 | 0;0;0;0;2;2;3;3;3;4;4;4;5;5;5;6;6;6;7;7;7;8;8;8 | Remove 4 from COAL market ",
            " OIL     | 5 | 0;0;0;0;0;0;0;0;0;0;0;4;5;5;5;6;6;6;7;7;7;8;8;8 | Remove 5 from OIL market  ",
            " TRASH   | 3 | 0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;8;8;8 | Remove 3 from TRASH market  ",
            " URANIUM | 1 | 0;0;0;0;0;0;0;0;0;0;0;16                        | Remove 1 from URANIUM market  ",
    })
    @TestCaseName("{3}")
    public void removeFromMarket(
            String type,
            Integer amount,
            String expected,
            String description) {

        // Arrange
        Resource resourceType = Resource.valueOf(type);
        List<Integer> expectedMarket = Arrays.stream(expected.split(";"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // Act
        List<Integer> actual = target.removeFromMarket(game.getResources().get(resourceType), amount);

        // Assert
        assertThat(actual).isEqualTo(expectedMarket);
    }

    @Test
    public void purchaseResources() {
        // Arrange
        ResourcePurchaseRequest request = new ResourcePurchaseRequest()
                .withPlayer(Color.BLUE)
                .withResources(ImmutableMap.of(
                        Resource.COAL, 1,
                        Resource.OIL, 1));

        Player player = new Player()
                .withMoney(50);

        when(playerService.findPlayerByColor(game, Color.BLUE))
                .thenReturn(player);

        // Act
        target.purchaseResources(game, request);

        // Assert
        assertThat(player.getMoney()).isEqualTo(46);

        List<Integer> coalMarket = Stream.concat(
                Stream.of(0),
                game.getResources().get(Resource.COAL).stream()
                        .skip(1)
                        .limit(game.getResources().get(Resource.COAL).size() - 1))
                .collect(Collectors.toList());
        verify(gameService, times(1)).setResourceMarket(game, Resource.COAL, coalMarket);

        List<Integer> oilMarket = Stream.concat(
                Stream.of(0, 0, 0, 0, 0, 0, 0),
                game.getResources().get(Resource.OIL).stream()
                        .skip(7)
                        .limit(game.getResources().get(Resource.OIL).size() - 1))
                .collect(Collectors.toList());
        verify(gameService, times(1)).setResourceMarket(game, Resource.OIL, oilMarket);
    }

    @Test
    @Parameters({
            " 0 | 1 | true  | Can not purchase this many COAL resources",
            " 1 | 1 | false |                                          ",
            " 1 | 0 | true  | Insufficient funds                       "
    })
    public void validateResourcePurchase(
            Integer allowed,
            Integer money,
            boolean expectException,
            String exceptionMessage) {
        // Arrange
        ResourcePurchaseRequest request = new ResourcePurchaseRequest()
                .withPlayer(Color.BLUE)
                .withResources(ImmutableMap.of(Resource.COAL, 1));

        Player player = new Player()
                .withColor(Color.BLUE)
                .withMoney(money);

        when(playerService.findPlayerByColor(game, Color.BLUE))
                .thenReturn(player);

        when(playerService.getMaxResourcesAllowedForPurchase(player, Resource.COAL))
                .thenReturn(allowed);

        // Act
        Throwable thrown = catchThrowable(() -> target.validateResourcePurchase(game, request));

        // Assert
        if (expectException) {
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasNoCause()
                    .hasMessage(exceptionMessage);
        } else {
            assertThat(thrown).isNull();
        }
    }

    @Test
    @Parameters({
            " 1 | COAL | true  | Not enough room on this plant to place these resources ",
            " 2 | COAL | false |                                                        ",
            " 2 | OIL  | true  | This plant does not allow this resource type           "
    })
    public void validateResourcePlace(
            Integer resources,
            Resource resource,
            boolean expectException,
            String exception) {
        // Arrange
        ResourcePlaceRequest request = new ResourcePlaceRequest()
                .withPlayer(Color.BLUE)
                .withResourcesToPlace(ImmutableMap.of(
                        1, ImmutableList.of(resource)
                ));

        PowerPlant plant = new PowerPlant()
                .withValue(1)
                .withResources(resources)
                .withResourceEnums(ImmutableSet.of(Resource.COAL));

        when(playerService.findPlayerByColor(game, Color.BLUE))
                .thenReturn(new Player()
                        .withPowerPlants(ImmutableList.of(plant))
                        .withResources(ImmutableMap.of(plant, ImmutableList.of(Resource.COAL))));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateResourcePlace(game, request));

        // Assert
        if (expectException) {
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasNoCause()
                    .hasMessage(exception);
        } else {
            assertThat(thrown).isNull();
        }
    }

}
