package ea.engine;

import ea.maps.America;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    public Integer createNewGame() {
        stateMap.put(
                currentId.get(),
                new State().withResources(America.initializeResources()));
        return currentId.getAndIncrement();
    }

}