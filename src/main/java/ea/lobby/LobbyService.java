package ea.lobby;

import com.google.common.collect.ImmutableList;
import ea.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;

    @Autowired
    public LobbyService(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    public Lobby create(User user) {
        Lobby lobby = new Lobby()
                .withUsers(ImmutableList.of(user.getUsername()))
                .withJoinCode(UUID.randomUUID().toString());

        return lobbyRepository.save(lobby);
    }

    public Lobby joinLobby(User user, String joinCode) {
        Lobby lobby = lobbyRepository.findByJoinCode(joinCode);
        List<String> currentPlayers = lobby.getUsers();
        List<String> newPlayers = Stream.concat(currentPlayers.stream(), Stream.of(user.getUsername()))
                .collect(Collectors.toList());
        lobby.withUsers(newPlayers);
        return lobby;
    }

    public List<Lobby> getLobbies() {
        return lobbyRepository.findAll();
    }

    public Lobby findById(String id) {
        return lobbyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find lobby with id " + id));
    }

}
