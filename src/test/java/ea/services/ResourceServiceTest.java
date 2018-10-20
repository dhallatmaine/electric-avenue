package ea.services;

import com.google.common.collect.ImmutableList;
import ea.api.ResourcePurchaseRequest;
import ea.data.Color;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class ResourceServiceTest {

    PlayerService playerService;
    ResourceService target;

    State game;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        target = new ResourceService(playerService);
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
    public void validateResourcePurchase() {
        // Arrange
        ResourcePurchaseRequest request = new ResourcePurchaseRequest()
                .withPlayer(Color.BLUE)
                .withResources(ImmutableList.of(Resource.COAL));

        when(playerService.getMaxResourcesAllowedForPurchase(any(), any()))
                .thenReturn(0);

        // Act
        Throwable thrown = catchThrowable(() -> target.validateResourcePurchase(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("Can not purchase this many COAL resources");
    }

}
