package ea.game;

import ea.lobby.LobbyService;
import ea.user.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    String gameId = "1";
    private GameService gameService;
    private LobbyService lobbyService;
    private UserService userService;
    private GameController target;

    @Before
    public void setup() {
        gameService = mock(GameService.class);
        lobbyService = mock(LobbyService.class);
        userService = mock(UserService.class);

        target = new GameController(gameService, lobbyService, userService);
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
