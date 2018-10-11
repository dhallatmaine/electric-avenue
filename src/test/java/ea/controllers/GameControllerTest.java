package ea.controllers;

import ea.services.GameService;
import ea.state.State;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    public void getGame() {
        // Arrange
        Integer id = 0;
        State state = mock(State.class);

        when(gameService.getGame(id))
                .thenReturn(state);

        // Act
        State actual = target.getGame(id);

        // Assert
        assertThat(actual).isEqualTo(state);
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
