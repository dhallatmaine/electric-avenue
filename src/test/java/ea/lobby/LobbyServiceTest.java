package ea.lobby;

import com.google.common.collect.ImmutableList;
import ea.user.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LobbyServiceTest {

    LobbyRepository lobbyRepository;

    LobbyService target;

    @Before
    public void setup() {
        lobbyRepository = mock(LobbyRepository.class);

        target = new LobbyService(lobbyRepository);
    }

    @Test
    public void create() {
        // Arrange
        User user = new User()
                .withUsername("username");

        // Act
        target.create(user);

        // Assert
        ArgumentCaptor<Lobby> argumentCaptor = ArgumentCaptor.forClass(Lobby.class);
        verify(lobbyRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getUsers()).contains("username");
        assertThat(argumentCaptor.getValue().getJoinCode()).isNotNull();
    }

    @Test
    public void join() {
        // Arrange
        Lobby lobby = new Lobby()
                .withJoinCode(UUID.randomUUID())
                .withUsers(ImmutableList.of("user-1"));

        User currentUser = new User()
                .withUsername("user-2");

        when(lobbyRepository.findByJoinCode(any()))
                .thenReturn(lobby);

        // Act
        Lobby actual = target.joinLobby(currentUser, lobby.getJoinCode());

        // Assert
        assertThat(actual.getUsers()).contains("user-2");
    }

}
