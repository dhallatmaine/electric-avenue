package ea.services;

import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.maps.America;
import ea.state.GameState;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameService {

    private GameState gameState;
    private PlayerService playerService;
    private PowerPlantService powerPlantService;
    private TurnOrderService turnOrderService;

    @Autowired
    public GameService(
            GameState gameState,
            PlayerService playerService,
            PowerPlantService powerPlantService,
            TurnOrderService turnOrderService) {
        this.gameState = gameState;
        this.playerService = playerService;
        this.powerPlantService = powerPlantService;
        this.turnOrderService = turnOrderService;
    }

    public Optional<State> getGame(Integer id) {
        return Optional.ofNullable(gameState.getById(id));
    }

    public State createGame() {
        List<PowerPlant> deck = new ArrayList<>(powerPlantService.createInitialPowerPlants());
        List<PowerPlant> currentMarket = powerPlantService.setupCurrentMarket(deck);
        deck.removeAll(currentMarket);
        List<PowerPlant> futureMarket = powerPlantService.setupFutureMarket(deck);
        deck.removeAll(futureMarket);

        List<PowerPlant> shuffledDeck = powerPlantService.shuffleDeck(deck, true);

        List<Player> players = playerService.setupPlayers();
        List<Color> turnOrder = turnOrderService.determineInitialTurnOrder(players);

        return gameState.createNewGame(
                America.initializeResources(),
                players,
                turnOrder,
                shuffledDeck,
                currentMarket,
                futureMarket);
    }

}
