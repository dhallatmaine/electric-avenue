package ea.services;

import ea.engine.GameState;
import ea.engine.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService {

    private GameState gameState;

    @Autowired
    public GameService(GameState gameState) {
        this.gameState = gameState;
    }

    public State getGame(Integer id) {
        return gameState.getById(id);
    }

    public Integer createGame() {
        return gameState.createNewGame();
    }

}