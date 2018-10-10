package ea.controllers;

import ea.engine.GameState;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    private GameState gameState;
    private GameController target;

    @Before
    public void setup() {
        gameState = mock(GameState.class);

        target = new GameController(gameState);
    }

    @Test
    public void createNewGame() {
        // Arrange
        when(gameState.createNewGame())
                .thenReturn(0);

        // Act
        Integer gameId = target.createNewGame();

        // Assert
        assertThat(gameId).isEqualTo(0);
    }

}
