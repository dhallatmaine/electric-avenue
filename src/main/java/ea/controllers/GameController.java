package ea.controllers;

import ea.exceptions.GameNotFoundException;
import ea.services.GameService;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{id}")
    public State getGame(@PathVariable("id") Integer id) {
        return gameService.getGame(id)
                .orElseThrow(GameNotFoundException::new);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public State createNewGame() {
        return gameService.createGame();
    }

}
