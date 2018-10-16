package ea.controllers;

import ea.services.GameService;
import ea.state.State;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

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
    public void getGame() {
        // Arrange
        Integer id = 0;
        State state = mock(State.class);

        when(gameService.getGame(id))
                .thenReturn(Optional.of(state));

        // Act
        State actual = target.getGame(id);

        // Assert
        assertThat(actual).isEqualTo(state);
    }

    @Test
    public void createNewGame() {
        // Arrange
        when(gameService.createGame())
                .thenReturn(new State().withGameId(1));

        // Act
        State actual = target.createNewGame();

        // Assert
        assertThat(actual).isEqualTo(new State().withGameId(1));
    }

}
