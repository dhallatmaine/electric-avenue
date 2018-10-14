package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.Color;
import ea.data.Player;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class TurnOrderServiceTest {

    ShuffleService shuffleService;
    TurnOrderService target;

    @Before
    public void setup() {
        shuffleService = mock(ShuffleService.class);
        target = new TurnOrderService(shuffleService);
    }

    @Test
    public void determineInitialTurnOrder() {
        // Arrange
        List<Player> players = ImmutableList.of(
                new Player().withColor(Color.BLACK),
                new Player().withColor(Color.BLUE),
                new Player().withColor(Color.GREEN));

        when(shuffleService.shuffle(players))
                .thenReturn(ImmutableList.of(
                        new Player().withColor(Color.BLUE),
                        new Player().withColor(Color.GREEN),
                        new Player().withColor(Color.BLACK)));

        // Act
        List<Color> actual = target.determineInitialTurnOrder(players);

        // Assert
        assertThat(actual).isEqualTo(ImmutableList.of(Color.BLUE, Color.GREEN, Color.BLACK));
    }

    @Test
    @Parameters({
            " BLACK | BLUE;BLACK;GREEN | GREEN | Next in list      ",
            " GREEN | BLUE;BLACK;GREEN | BLUE  | Beginning of list ",
    })
    @TestCaseName("{3}")
    public void getNextPlayer(
            Color player,
            String orderStr,
            Color expected,
            String description) {
        // Arrange
        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        // Act
        Color actual = target.getNextPlayer(order, player);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Parameters({
            " GREEN | GREEN;BLACK;BLUE;YELLOW | GREEN;BLACK;BLUE;YELLOW | From beginning of list      ",
            " GREEN | BLACK;GREEN;BLUE;YELLOW | GREEN;BLUE;YELLOW;BLACK | From second element of list ",
            " GREEN | BLACK;BLUE;GREEN;YELLOW | GREEN;YELLOW;BLACK;BLUE | From third element of list  ",
            " GREEN | BLACK;BLUE;YELLOW;GREEN | GREEN;BLACK;BLUE;YELLOW | From last element of list   ",
    })
    @TestCaseName("{3}")
    public void determineOrderStartingAtPlayer(
            Color start,
            String orderStr,
            String expectedOrderStr,
            String description) {
        // Arrange
        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        List<Color> expected = Arrays.stream(expectedOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        // Act
        List<Color> actual = target.determineOrderStartingAtPlayer(order, start);

        assertThat(actual).isEqualTo(expected);
    }

}
