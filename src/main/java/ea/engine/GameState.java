package ea.engine;

import ea.data.PowerPlant;
import ea.engine.phase.impl.BasePhaseImpl;
import ea.engine.phase.impl.BureaucracyPhase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class GameState {

    private BasePhaseImpl phase;
    private Integer round;
    private List<PowerPlant> deckPlants;
    private List<PowerPlant> currentMarketPlants;
    private List<PowerPlant> futureMarketPlants;

    public GameState() {
        round = 1;
    }

    public BasePhaseImpl getPhase() {
        return phase;
    }

    public void setPhase(BasePhaseImpl phase) {
        this.phase = phase;
        if (this.phase instanceof BureaucracyPhase) {
            round++;
        }
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public List<PowerPlant> getDeckPlants() {
        return deckPlants;
    }

    public void setDeckPlants(List<PowerPlant> deckPlants) {
        this.deckPlants = deckPlants;
    }

    public List<PowerPlant> getCurrentMarketPlants() {
        return currentMarketPlants;
    }

    public void setCurrentMarketPlants(List<PowerPlant> currentMarketPlants) {
        this.currentMarketPlants = currentMarketPlants;
    }

    public List<PowerPlant> getFutureMarketPlants() {
        return futureMarketPlants;
    }

    public void setFutureMarketPlants(List<PowerPlant> futureMarketPlants) {
        this.futureMarketPlants = futureMarketPlants;
    }
}