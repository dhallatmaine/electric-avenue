package ea.state;

import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.ResourceEnum;
import ea.maps.America;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("singleton")
public class GameState {

    private AtomicInteger currentId = new AtomicInteger();
    private Map<Integer, State> stateMap = new HashMap<>();

    public Map<Integer, State> getStateMap() {
        return stateMap;
    }

    public State getById(Integer id) {
        return stateMap.get(id);
    }

    public Integer createNewGame(
            Map<ResourceEnum, List<Integer>> resources,
            List<Player> players,
            List<PowerPlant> deck,
            List<PowerPlant> currentMarket,
            List<PowerPlant> futureMarket) {
        stateMap.put(
                currentId.get(),
                new State()
                        .withResources(resources)
                        .withPlayers(players)
                        .withDeckPlants(deck)
                        .withCurrentMarketPlants(currentMarket)
                        .withFutureMarketPlants(futureMarket));
        return currentId.getAndIncrement();
    }

}