package ea.game;

import ea.player.Color;
import ea.player.Player;
import ea.powerplant.PowerPlant;
import ea.maps.City;
import ea.user.User;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    @Id
    private String gameId;
    private Phase phase;
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

    public Phase getPhase() {
        return phase;
    }

    public Game withPhase(Phase phase) {
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
