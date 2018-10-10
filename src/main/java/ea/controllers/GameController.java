package ea.controllers;

import ea.engine.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameState gameState;

    @Autowired
    public GameController(GameState gameState) {
        this.gameState = gameState;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer createNewGame() {
        return gameState.createNewGame();
    }

}
