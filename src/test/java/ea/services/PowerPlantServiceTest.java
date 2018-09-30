package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.PowerPlant;
import ea.engine.GameState;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class PowerPlantServiceTest {

    private GameState gameState;
    private PowerPlantService target;

    @Before
    public void setup() {
        gameState = mock(GameState.class);

        target = new PowerPlantService(gameState);
    }

    @Test
    @Parameters({
            " true  | 13 Card on top of deck ",
            " false | 13 Card not on top of the deck "
    })
    public void shuffleDeck(
            boolean thirteen,
            String description) {
        // Given
        List<PowerPlant> plantsToShuffle = ImmutableList.of(
                new PowerPlant().withValue(10),
                new PowerPlant().withValue(11),
                new PowerPlant().withValue(12),
                new PowerPlant().withValue(13));

        // Act
        List<PowerPlant> shuffled = target.shuffleDeck(plantsToShuffle, thirteen);

        // Assert
        assertThat(shuffled.size()).isEqualTo(5);
        if (thirteen) {
            assertThat(shuffled.get(0).getValue()).isEqualTo(13);
        }
    }

}
