package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.PowerPlant;
import ea.engine.GameState;
import ea.engine.State;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.LinkedList;
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

    @Test
    public void flipNewCard() {
        // Given
        State game = new State()
                .withDeckPlants(new LinkedList<>(Arrays.asList(
                        new PowerPlant().withValue(13),
                        new PowerPlant().withValue(20))))
                .withCurrentMarketPlants(new LinkedList<>(Arrays.asList(
                        new PowerPlant().withValue(3),
                        new PowerPlant().withValue(4),
                        new PowerPlant().withValue(5),
                        new PowerPlant().withValue(6))))
                .withFutureMarketPlants(new LinkedList<>(Arrays.asList(
                        new PowerPlant().withValue(7),
                        new PowerPlant().withValue(8),
                        new PowerPlant().withValue(9),
                        new PowerPlant().withValue(10))));

        when(gameState.getById(any()))
                .thenReturn(game);

        // Act
        target.flipNewCard(1, new PowerPlant().withValue(3));

        // Assert
        assertThat(game.getDeckPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(20)));
        assertThat(game.getCurrentMarketPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(4),
                new PowerPlant().withValue(5),
                new PowerPlant().withValue(6),
                new PowerPlant().withValue(7)));
        assertThat(game.getFutureMarketPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(8),
                new PowerPlant().withValue(9),
                new PowerPlant().withValue(10),
                new PowerPlant().withValue(13)));
    }

}
