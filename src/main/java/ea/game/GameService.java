package ea.game;

import ea.lobby.Lobby;
import ea.maps.America;
import ea.player.Color;
import ea.player.Player;
import ea.powerplant.PowerPlant;
import ea.player.PlayerService;
import ea.powerplant.PowerPlantService;
import ea.resource.Resource;
import ea.turnorder.TurnOrderService;
import ea.user.User;
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
                futureMarket,
                Phase.POWER_PLANT_BIDS);
    }

    public Game createGame(List<User> users) {
        List<PowerPlant> deck = new ArrayList<>(powerPlantService.createInitialPowerPlants());
        List<PowerPlant> currentMarket = powerPlantService.setupCurrentMarket(deck);
        deck.removeAll(currentMarket);
        List<PowerPlant> futureMarket = powerPlantService.setupFutureMarket(deck);
        deck.removeAll(futureMarket);

        List<PowerPlant> shuffledDeck = powerPlantService.shuffleDeck(deck, true);

        List<Player> players = playerService.setupPlayers(users);
        List<Color> turnOrder = turnOrderService.determineInitialTurnOrder(players);

        return gameDataStore.create(
                America.getAmericaMap(),
                America.initializeResources(),
                players,
                turnOrder,
                shuffledDeck,
                currentMarket,
                futureMarket,
                Phase.POWER_PLANT_BIDS);
    }

    public void save(Game game) {
        gameDataStore.save(game);
    }

    public void setResourceMarket(Game game, Resource resource, List<Integer> market) {
        // TODO: Make this immutable
        game.getResources().put(resource.name(), market);
    }

    public void setPlayerResources(List<Resource> resources, Player player, PowerPlant plant) {
        // TODO: Make this immutable
        player.getResources().put(plant.getValue(), resources);
    }

}
