package ea.lobby;

import ea.user.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
        assertThat(argumentCaptor.getValue().getJoinCode()).isNotBlank();
    }

}
