package ea.state;

import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;

import java.util.*;

public class State {

    private String phase;
    private Integer round;
    private List<PowerPlant> deckPlants;
    private List<PowerPlant> currentMarketPlants;
    private List<PowerPlant> futureMarketPlants;
    private Map<Resource, List<Integer>> resources;
    private List<Player> players;
    private List<Color> turnOrder;

    public State() {
        round = 1;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
        if ("BureaucracyPhase".equalsIgnoreCase(this.phase)) {
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

    public State withDeckPlants(List<PowerPlant> deckPlants) {
        this.deckPlants = deckPlants;
        return this;
    }

    public List<PowerPlant> getCurrentMarketPlants() {
        return currentMarketPlants;
    }

    public State withCurrentMarketPlants(List<PowerPlant> currentMarketPlants) {
        this.currentMarketPlants = currentMarketPlants;
        return this;
    }

    public List<PowerPlant> getFutureMarketPlants() {
        return futureMarketPlants;
    }

    public State withFutureMarketPlants(List<PowerPlant> futureMarketPlants) {
        this.futureMarketPlants = futureMarketPlants;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public State withPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public Map<Resource, List<Integer>> getResources() {
        return resources;
    }

    public State withResources(Map<Resource, List<Integer>> resources) {
        this.resources = resources;
        return this;
    }

    public List<Color> getTurnOrder() {
        return turnOrder;
    }

    public State withTurnOrder(List<Color> turnOrder) {
        this.turnOrder = turnOrder;
        return this;
    }
}
