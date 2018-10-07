package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.Resource;
import ea.data.ResourceEnum;
import ea.engine.GameState;
import ea.engine.State;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class ResourceServiceTest {

    private ResourceService target;
    private GameState gameState;
    private State state;

    @Before
    public void setup() {
        gameState = mock(GameState.class);
        target = new ResourceService(gameState);
        state = new State();
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
        // When
        ResourceEnum resourceType = ResourceEnum.valueOf(type);

        // Act
        Integer actual = target.getPrice(state.getResources().get(resourceType), amount);

        // Assert
        assertThat(actual).isEqualTo(expectedPrice);
    }

}
