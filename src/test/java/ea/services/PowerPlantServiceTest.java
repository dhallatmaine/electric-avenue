package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.PowerPlant;
import ea.engine.GameState;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PowerPlantServiceTest {

    private GameState gameState;
    private PowerPlantService target;

    @Before
    public void setup() {
        gameState = mock(GameState.class);

        target = new PowerPlantService(gameState);
    }

    @Test
    public void shuffleDeck() {
        // Given
        List<PowerPlant> plantsToShuffle = ImmutableList.of(
                new PowerPlant().withValue(10),
                new PowerPlant().withValue(11),
                new PowerPlant().withValue(12),
                new PowerPlant().withValue(13));

        // Act
        List<PowerPlant> shuffled = target.shuffleDeck(plantsToShuffle, true);

        // Assert
        assertThat(shuffled.size()).isEqualTo(5);
    }

}
