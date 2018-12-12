package ea.state;

import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.maps.City;
import org.springframework.data.annotation.Id;

import java.util.*;

public class Game {

    @Id
    private String gameId;
    private String phase;
    private Integer round;
    private Map<String, City> gameMap;
    private List<PowerPlant> deckPlants;
    private List<PowerPlant> currentMarketPlants;
    private List<PowerPlant> futureMarketPlants;
    private Map<String, List<Integer>> resources;
    private List<Player> players;
    private List<Color> turnOrder;
    private Color currentTurn;
    private Map<Integer, BidRound> bidRounds = new HashMap<>();

    public Game() {
        round = 1;
    }

    public String getGameId() {
        return gameId;
    }

    public Game withGameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getPhase() {
        return phase;
    }

    public Game withPhase(String phase) {
        this.phase = phase;
        return this;
    }

    public Integer getRound() {
        return round;
    }

    public Game withRound(Integer round) {
        this.round = round;
        return this;
    }

    public Map<String, City> getGameMap() {
        return gameMap;
    }

    public Game withGameMap(Map<String, City> gameMap) {
        this.gameMap = gameMap;
        return this;
    }

    public List<PowerPlant> getDeckPlants() {
        return deckPlants;
    }

    public Game withDeckPlants(List<PowerPlant> deckPlants) {
        this.deckPlants = deckPlants;
        return this;
    }

    public List<PowerPlant> getCurrentMarketPlants() {
        return currentMarketPlants;
    }

    public Game withCurrentMarketPlants(List<PowerPlant> currentMarketPlants) {
        this.currentMarketPlants = currentMarketPlants;
        return this;
    }

    public List<PowerPlant> getFutureMarketPlants() {
        return futureMarketPlants;
    }

    public Game withFutureMarketPlants(List<PowerPlant> futureMarketPlants) {
        this.futureMarketPlants = futureMarketPlants;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Game withPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public Map<String, List<Integer>> getResources() {
        return resources;
    }

    public Game withResources(Map<String, List<Integer>> resources) {
        this.resources = resources;
        return this;
    }

    public List<Color> getTurnOrder() {
        return turnOrder;
    }

    public Game withTurnOrder(List<Color> turnOrder) {
        this.turnOrder = turnOrder;
        return this;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Game withCurrentTurn(Color currentTurn) {
        this.currentTurn = currentTurn;
        return this;
    }

    public Map<Integer, BidRound> getBidRounds() {
        return bidRounds;
    }

    public Game withBidRounds(Map<Integer, BidRound> bidRounds) {
        this.bidRounds = bidRounds;
        return this;
    }
}
