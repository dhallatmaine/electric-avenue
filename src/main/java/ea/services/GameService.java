package ea.services;

import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.maps.America;
import ea.state.GameDataStore;
import ea.state.Game;
import ea.state.MongoGameDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameService {

    private MongoGameDataStore gameDataStore;
    private PlayerService playerService;
    private PowerPlantService powerPlantService;
    private TurnOrderService turnOrderService;

    @Autowired
    public GameService(
            MongoGameDataStore gameDataStore,
            PlayerService playerService,
            PowerPlantService powerPlantService,
            TurnOrderService turnOrderService) {
        this.gameDataStore = gameDataStore;
        this.playerService = playerService;
        this.powerPlantService = powerPlantService;
        this.turnOrderService = turnOrderService;
    }

    public Optional<Game> getGame(String id) {
        return gameDataStore.getGameById(id);
    }

    public Game createGame() {
        List<PowerPlant> deck = new ArrayList<>(powerPlantService.createInitialPowerPlants());
        List<PowerPlant> currentMarket = powerPlantService.setupCurrentMarket(deck);
        deck.removeAll(currentMarket);
        List<PowerPlant> futureMarket = powerPlantService.setupFutureMarket(deck);
        deck.removeAll(futureMarket);

        List<PowerPlant> shuffledDeck = powerPlantService.shuffleDeck(deck, true);

        List<Player> players = playerService.setupPlayers();
        List<Color> turnOrder = turnOrderService.determineInitialTurnOrder(players);

        return gameDataStore.create(
                America.getAmericaMap(),
                America.initializeResources(),
                players,
                turnOrder,
                shuffledDeck,
                currentMarket,
                futureMarket);
    }

    public void save(Game game) {
        gameDataStore.save(game);
    }

    public void setResourceMarket(Game game, Resource resource, List<Integer> market) {
        // TODO: Make this immutable
        game.getResources().put(resource, market);
    }

    public void setPlayerResources(List<Resource> resources, Player player, PowerPlant plant) {
        // TODO: Make this immutable
        player.getResources().put(plant.getValue(), resources);
    }

}
