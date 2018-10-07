package ea.services;

import ea.data.ResourceEnum;
import ea.engine.GameState;
import ea.engine.State;
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

@RunWith(JUnitParamsRunner.class)
public class ResourceServiceTest {

    private ResourceService target;
    private GameState gameState;
    private State state;

    @Before
    public void setup() {
        gameState = new GameState();
        int gameId = gameState.createNewGame();
        state = gameState.getById(gameId);
        target = new ResourceService(gameState);
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
        // Given
        ResourceEnum resourceType = ResourceEnum.valueOf(type);

        // Act
        Integer actual = target.getPrice(state.getResources().get(resourceType), amount);

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
        // Given
        ResourceEnum resourceType = ResourceEnum.valueOf(type);
        List<Integer> expectedMarket = Arrays.stream(expected.split(";"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // Act
        List<Integer> actual = target.removeFromMarket(state.getResources().get(resourceType), amount);

        // Assert
        assertThat(actual).isEqualTo(expectedMarket);
    }

}
