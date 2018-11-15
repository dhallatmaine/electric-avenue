package ea.state;

import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("singleton")
public class GameDataStore {

    private AtomicInteger currentId = new AtomicInteger();
    private Map<Integer, Game> stateMap = new HashMap<>();

    public Game getById(Integer id) {
        return stateMap.get(id);
    }

    public Map<Integer, Game> getStateMap() {
        return stateMap;
    }

    public Game createNewGame(
            Map<Resource, List<Integer>> resources,
            List<Player> players,
            List<Color> turnOrder,
            List<PowerPlant> deck,
            List<PowerPlant> currentMarket,
            List<PowerPlant> futureMarket) {
        stateMap.put(
                currentId.get(),
                new Game()
                        .withGameId(currentId.get())
                        .withPhase("PowerPlant")
                        .withResources(resources)
                        .withPlayers(players)
                        .withTurnOrder(turnOrder)
                        .withDeckPlants(deck)
                        .withCurrentMarketPlants(currentMarket)
                        .withFutureMarketPlants(futureMarket)
                        .withCurrentTurn(turnOrder.get(0)));
        return stateMap.get(currentId.getAndIncrement());
    }

}