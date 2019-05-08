package ea.game;

import ea.game.GameNotFoundException;
import ea.game.GameService;
import ea.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
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

}
