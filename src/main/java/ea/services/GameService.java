package ea.services;

import ea.data.Player;
import ea.data.PowerPlant;
import ea.maps.America;
import ea.state.GameState;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameService {

    private GameState gameState;
    private PlayerService playerService;
    private PowerPlantService powerPlantService;

    @Autowired
    public GameService(
            GameState gameState,
            PlayerService playerService,
            PowerPlantService powerPlantService) {
        this.gameState = gameState;
        this.playerService = playerService;
        this.powerPlantService = powerPlantService;
    }

    public State getGame(Integer id) {
        return gameState.getById(id);
    }

    public Integer createGame() {
        List<PowerPlant> deck = powerPlantService.createInitialPowerPlants();
        List<PowerPlant> currentMarket = powerPlantService.setupCurrentMarket(deck);
        deck.removeAll(currentMarket);
        List<PowerPlant> futureMarket = powerPlantService.setupFutureMarket(deck);
        deck.removeAll(futureMarket);

        List<PowerPlant> shuffledDeck = powerPlantService.shuffleDeck(deck, true);

        List<Player> players = playerService.setupPlayers();

        return gameState.createNewGame(
                America.initializeResources(),
                players,
                shuffledDeck,
                currentMarket,
                futureMarket);
    }

}
