package ea.controllers;

import ea.services.GameService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    private GameService gameService;
    private GameController target;

    @Before
    public void setup() {
        gameService = mock(GameService.class);

        target = new GameController(gameService);
    }

    @Test
    public void createNewGame() {
        // Arrange
        when(gameService.createGame())
                .thenReturn(0);

        // Act
        Integer gameId = target.createNewGame();

        // Assert
        assertThat(gameId).isEqualTo(0);
    }

}
