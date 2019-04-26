package ea.controllers;

import ea.services.GameService;
import ea.state.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    String gameId = "1";
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
        Game game = mock(Game.class);

        when(gameService.getGame(gameId))
                .thenReturn(Optional.of(game));

        // Act
        Game actual = target.getGame(gameId);

        // Assert
        assertThat(actual).isEqualTo(game);
    }

    @Test
    public void createNewGame() {
        // Arrange
        when(gameService.createGame())
                .thenReturn(new Game().withGameId(gameId));

        // Act
        Game actual = target.createNewGame();

        // Assert
        assertThat(actual)
                .isEqualToComparingFieldByFieldRecursively(new Game().withGameId(gameId).withRound(1));
    }

}
