package ea.services;

import ea.state.GameState;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService {

    private GameState gameState;
    private PlayerService playerService;

    @Autowired
    public GameService(
            GameState gameState,
            PlayerService playerService) {
        this.gameState = gameState;
        this.playerService = playerService;
    }

    public State getGame(Integer id) {
        return gameState.getById(id);
    }

    public Integer createGame() {
        return gameState.createNewGame(playerService.setupPlayers());
    }

}
