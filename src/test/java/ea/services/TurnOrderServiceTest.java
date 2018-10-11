package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.Color;
import ea.data.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}
