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

    //@PostMapping
    //public Lobby create() {
        //return lobbyService.create(currentUserService.getCurrentUser());
    //}

    @PostMapping(path = "/join/{joinCode}")
    public Lobby join(@PathVariable("joinCode") UUID joinCode) {
        System.out.println("WHAT THE FUCK");
        return lobbyService.joinLobby(currentUserService.getCurrentUser(), joinCode.toString());
    }

    @GetMapping
    public List<Lobby> viewLobbies() {
        return lobbyService.getLobbies();
    }

}
