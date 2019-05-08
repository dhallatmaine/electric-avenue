package ea.controllers;

import ea.exceptions.GameNotFoundException;
import ea.services.GameService;
import ea.state.Game;
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
