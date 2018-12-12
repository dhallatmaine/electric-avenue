package ea.state;

import ea.GameRepository;
import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.maps.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MongoGameDataStore {

    private GameRepository gameRepository;

    @Autowired
    public MongoGameDataStore(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create(Map<String, City> gameMap,
                         Map<String, List<Integer>> resources,
                         List<Player> players,
                         List<Color> turnOrder,
                         List<PowerPlant> deck,
                         List<PowerPlant> currentMarket,
                         List<PowerPlant> futureMarket) {

        Game game = new Game()
                .withPhase("PowerPlant")
                .withGameMap(gameMap)
                .withResources(resources)
                .withPlayers(players)
                .withTurnOrder(turnOrder)
                .withDeckPlants(deck)
                .withCurrentMarketPlants(currentMarket)
                .withFutureMarketPlants(futureMarket)
                .withCurrentTurn(turnOrder.get(0));

        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

}
