package ea.lobby;

import ea.user.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/lobby")
public class LobbyController {

    private final CurrentUserService currentUserService;
    private LobbyService lobbyService;

    @Autowired
    public LobbyController(CurrentUserService currentUserService, LobbyService lobbyService) {
        this.currentUserService = currentUserService;
        this.lobbyService = lobbyService;
    }

    @PostMapping
    public Lobby create() {
        return lobbyService.create(currentUserService.getCurrentUser());
    }

    @GetMapping
    public List<Lobby> viewLobbies() {
        return lobbyService.getLobbies();
    }

}
