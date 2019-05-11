package ea.lobby;

import com.google.common.collect.ImmutableList;
import ea.user.CurrentUserService;
import ea.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class LobbyControllerTest {

    CurrentUserService currentUserService;
    LobbyService lobbyService;
    LobbyController target;

    @Before
    public void setup() {
        currentUserService = mock(CurrentUserService.class);
        lobbyService = mock(LobbyService.class);

        target = new LobbyController(
                currentUserService,
                lobbyService);
    }

    @Test
    public void create() {
        // Arrange
        User currentUser = mock(User.class);

        when(currentUserService.getCurrentUser())
                .thenReturn(currentUser);

        // Act
        target.create();

        // Assert
        verify(lobbyService).create(currentUser);
    }

    @Test
    public void join() {
        // Arrange
        UUID joinCode = UUID.randomUUID();

        User currentUser = mock(User.class);

        when(currentUserService.getCurrentUser())
                .thenReturn(currentUser);

        // Act
        target.join(joinCode);

        // Assert
        verify(lobbyService).joinLobby(currentUser, joinCode);
    }

    @Test
    public void viewLobbies() {
        // Arrange
        List<Lobby> lobbies = ImmutableList.of(mock(Lobby.class), mock(Lobby.class), mock(Lobby.class));

        User currentUser = mock(User.class);

        when(currentUserService.getCurrentUser())
                .thenReturn(currentUser);

        when(lobbyService.getLobbies(currentUser))
                .thenReturn(lobbies);

        // Act
        target.viewLobbies();

        // Assert
        verify(lobbyService).getLobbies(currentUser);
    }

}
