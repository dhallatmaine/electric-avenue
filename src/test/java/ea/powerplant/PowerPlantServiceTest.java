package ea.powerplant;

import com.google.common.collect.ImmutableList;
import ea.powerplant.PowerPlant;
import ea.game.Game;
import ea.powerplant.PowerPlantService;
import ea.turnorder.ShuffleService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class PowerPlantServiceTest {

    ShuffleService shuffleService;
    PowerPlantService target;

    @Before
    public void setup() {
        shuffleService = mock(ShuffleService.class);
        target = new PowerPlantService(shuffleService);
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

        List<PowerPlant> shuffled = ImmutableList.of(
                new PowerPlant().withValue(12),
                new PowerPlant().withValue(11),
                new PowerPlant().withValue(13),
                new PowerPlant().withValue(10));

        when(shuffleService.shuffle(plantsToShuffle))
                .thenReturn(shuffled);

        // Act
        List<PowerPlant> actual = target.shuffleDeck(plantsToShuffle, thirteen);

        // Assert
        assertThat(actual.size()).isEqualTo(5);
        if (thirteen) {
            assertThat(actual).isEqualTo(
                    ImmutableList.of(
                            new PowerPlant().withValue(13),
                            new PowerPlant().withValue(12),
                            new PowerPlant().withValue(11),
                            new PowerPlant().withValue(10),
                            new PowerPlant().withValue(0)));
        } else {
            assertThat(actual).isEqualTo(
                    ImmutableList.of(
                            new PowerPlant().withValue(12),
                            new PowerPlant().withValue(11),
                            new PowerPlant().withValue(13),
                            new PowerPlant().withValue(10),
                            new PowerPlant().withValue(0)));
        }
    }

    @Test
    public void flipNewCard() {
        // Arrange
        Game game = new Game()
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
        List<PowerPlant> deck = ImmutableList.of(
                new PowerPlant().withValue(3),
                new PowerPlant().withValue(4),
                new PowerPlant().withValue(5),
                new PowerPlant().withValue(6),
                new PowerPlant().withValue(7),
                new PowerPlant().withValue(8));

        // Act
        List<PowerPlant> actual = target.setupCurrentMarket(deck);

        // Assert
        assertThat(actual).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(3),
                new PowerPlant().withValue(4),
                new PowerPlant().withValue(5),
                new PowerPlant().withValue(6)
        ));
    }

    @Test
    public void setupFutureMarket() {
        // Arrange
        List<PowerPlant> deck = ImmutableList.of(
                new PowerPlant().withValue(7),
                new PowerPlant().withValue(8),
                new PowerPlant().withValue(9),
                new PowerPlant().withValue(10),
                new PowerPlant().withValue(11),
                new PowerPlant().withValue(12));

        // Act
        List<PowerPlant> actual = target.setupFutureMarket(deck);

        // Assert
        assertThat(actual).isEqualTo(ImmutableList.of(
                new PowerPlant().withValue(7),
                new PowerPlant().withValue(8),
                new PowerPlant().withValue(9),
                new PowerPlant().withValue(10)
        ));
    }

}
