package ea.lobby;

import ea.user.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/lobby")
public class LobbyController {

    private final CurrentUserService currentUserService;
    private final LobbyService lobbyService;

    @Autowired
    public LobbyController(CurrentUserService currentUserService, LobbyService lobbyService) {
        this.currentUserService = currentUserService;
        this.lobbyService = lobbyService;
    }

    @PostMapping
    public Lobby create() {
        return lobbyService.create(currentUserService.getCurrentUser());
    }

    @PutMapping("/{code}")
    public Lobby join(@PathVariable UUID code) {
        return lobbyService.joinLobby(currentUserService.getCurrentUser(), code);
    }

    @GetMapping
    public List<Lobby> viewLobbies() {
        return lobbyService.getLobbies(currentUserService.getCurrentUser());
    }

}
