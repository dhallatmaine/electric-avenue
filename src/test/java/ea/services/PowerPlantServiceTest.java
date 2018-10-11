package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.PowerPlant;
import ea.state.State;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PowerPlantServiceTest {

    private PowerPlantService target;

    @Before
    public void setup() {
        target = new PowerPlantService();
    }

    @Test
    @Parameters({
            " true  | 13 Card on top of deck ",
            " false | 13 Card not on top of the deck "
    })
    @TestCaseName("{1}")
    public void shuffleDeck(
            boolean thirteen,
            String description) {

        // Arrange
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
        // Arrange
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

        // Act
        target.flipNewCard(game, new PowerPlant().withValue(3));

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

    @Test
    public void setupCurrentMarket() {
        // Arrange
        State state = new State()
                .withDeckPlants(new LinkedList<>(Arrays.asList(
                        new PowerPlant().withValue(3),
                        new PowerPlant().withValue(4),
                        new PowerPlant().withValue(5),
                        new PowerPlant().withValue(6),
                        new PowerPlant().withValue(7),
                        new PowerPlant().withValue(8)
                )));

        // Act
        target.setupCurrentMarket(state);

        // Assert
        assertThat(state.getCurrentMarketPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(3),
                new PowerPlant().withValue(4),
                new PowerPlant().withValue(5),
                new PowerPlant().withValue(6)
        ));
        assertThat(state.getDeckPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(7),
                new PowerPlant().withValue(8)
        ));
    }

    @Test
    public void setupFutureMarket() {
        // Arrange
        State state = new State()
                .withDeckPlants(new LinkedList<>(Arrays.asList(
                        new PowerPlant().withValue(7),
                        new PowerPlant().withValue(8),
                        new PowerPlant().withValue(9),
                        new PowerPlant().withValue(10),
                        new PowerPlant().withValue(11),
                        new PowerPlant().withValue(12)
                )));

        // Act
        target.setupFutureMarket(state);

        // Assert
        assertThat(state.getFutureMarketPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(7),
                new PowerPlant().withValue(8),
                new PowerPlant().withValue(9),
                new PowerPlant().withValue(10)
        ));
        assertThat(state.getDeckPlants()).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(11),
                new PowerPlant().withValue(12)
        ));
    }

}
