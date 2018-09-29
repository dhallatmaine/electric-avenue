package ea.engine;

import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.engine.phase.impl.BasePhaseImpl;
import ea.engine.phase.impl.BureaucracyPhase;

import java.util.List;

public class State {

    private BasePhaseImpl phase;
    private Integer round;
    private List<PowerPlant> deckPlants;
    private List<PowerPlant> currentMarketPlants;
    private List<PowerPlant> futureMarketPlants;

    private List<Resource> coal;
    private List<Resource> oil;
    private List<Resource> trash;
    private List<Resource> uranium;

    private List<Player> players;

    public State() {
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

    public List<Resource> getCoal() {
        return coal;
    }

    public void setCoal(List<Resource> coal) {
        this.coal = coal;
    }

    public List<Resource> getOil() {
        return oil;
    }

    public void setOil(List<Resource> oil) {
        this.oil = oil;
    }

    public List<Resource> getTrash() {
        return trash;
    }

    public void setTrash(List<Resource> trash) {
        this.trash = trash;
    }

    public List<Resource> getUranium() {
        return uranium;
    }

    public void setUranium(List<Resource> uranium) {
        this.uranium = uranium;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
