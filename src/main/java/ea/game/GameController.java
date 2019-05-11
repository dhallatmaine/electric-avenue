package ea.game;

import ea.lobby.Lobby;
import ea.lobby.LobbyService;
import ea.user.User;
import ea.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;
    private LobbyService lobbyService;
    private UserService userService;

    @Autowired
    public GameController(GameService gameService, LobbyService lobbyService, UserService userService) {
        this.gameService = gameService;
        this.lobbyService = lobbyService;
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Game getGame(@PathVariable("id") String id) {
        return gameService.getGame(id)
                .orElseThrow(GameNotFoundException::new);
    }

    @PostMapping
    public Game createNewGame() {
        return gameService.createGame();
    }

    @PostMapping("/{lobbyId}")
    public Game createNewGame(@PathVariable String lobbyId) {
        Lobby lobby = lobbyService.findById(lobbyId);
        List<User> users = lobby.getUsers().stream()
                .map(user -> userService.findByUsername(user))
                .collect(Collectors.toList());
        return gameService.createGame(users);
    }

}
