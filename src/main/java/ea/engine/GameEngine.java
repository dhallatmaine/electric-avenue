package ea.engine;

import ea.data.Player;
import ea.engine.phase.impl.BuildingPhase;
import ea.engine.phase.impl.BureaucracyPhase;
import ea.engine.phase.impl.PowerPlantPhase;
import ea.engine.phase.impl.ResourcePhase;
import ea.maps.America;
import ea.services.*;
import ea.views.DefaultView;
import ea.views.PlayerView;
import ea.views.PowerPlantsView;
import ea.views.ResourcesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class GameEngine implements CommandLineRunner {

  private final RulesService rulesService;
  private final America america;
  private final PowerPlantService powerPlantService;
  private final PlayerService playerService;
  private final ResourceService resourceService;
  private final BureaucracyPhase bureaucracyPhase;
  private final GameState gameState;

  @Autowired
  public GameEngine(RulesService rulesService,
                    America america,
                    PowerPlantService powerPlantService,
                    PlayerService playerService,
                    ResourceService resourceService,
                    BureaucracyPhase bureaucracyPhase,
                    GameState gameState) {
    this.rulesService = rulesService;
    this.america = america;
    this.powerPlantService = powerPlantService;
    this.playerService = playerService;
    this.resourceService = resourceService;
    this.bureaucracyPhase = bureaucracyPhase;
    this.gameState = gameState;
  }

  //views
  private PowerPlantsView powerPlantsView;
  private PlayerView playerView;
  private ResourcesView resourcesView;
  private DefaultView defaultView;

  //phases
  private PowerPlantPhase powerPlantPhase;
  private ResourcePhase resourcePhase;
  private BuildingPhase buildingPhase;

  @Override
  public void run(String... args) {
    powerPlantsView = new PowerPlantsView();
    playerView = new PlayerView();
    resourcesView = new ResourcesView(gameState);
    defaultView = new DefaultView();
    powerPlantPhase = new PowerPlantPhase(gameState, powerPlantService, playerService);
    resourcePhase = new ResourcePhase(gameState, resourceService, playerService);
    buildingPhase = new BuildingPhase(gameState, america, playerService);

    Integer currentGameId = gameState.createNewGame();
    State currentGame = gameState.getById(currentGameId);
    currentGame.setRound(1);

    resourceService.initializeResources(currentGameId);

    america.initializeCities();

    currentGame.setDeckPlants(powerPlantService.createInitialPowerPlants());
    powerPlantService.setupMarket(currentGameId);
    powerPlantService.shuffleDeck(currentGameId, true);

    currentGame.setPlayers(playerService.setupPlayers());

    powerPlantsView.displayCurrentMarketPlants(currentGame.getCurrentMarketPlants());
    powerPlantsView.displayFutureMarketPlants(currentGame.getFutureMarketPlants());
    powerPlantsView.displayDeckPlants(currentGame.getDeckPlants());

    while (!endGame(currentGame)) {
      defaultView.outln("ROUND: " + currentGame.getRound());
      playerView.displayPlayersAttributes(currentGame.getPlayers());
      resourcesView.displayResourceMarket(currentGameId);

      playerView.displayPlayersCities(currentGame.getPlayers());

      // Power Plant Phase
      powerPlantPhase.initiate(currentGameId, currentGame.getPlayers());

      powerPlantsView.displayCurrentMarketPlants(currentGame.getCurrentMarketPlants());
      powerPlantsView.displayFutureMarketPlants(currentGame.getFutureMarketPlants());
      powerPlantsView.displayDeckPlants(currentGame.getDeckPlants());
      playerView.displayPlayersAttributes(currentGame.getPlayers());

      // Resource Phase
      resourcePhase.initiate(currentGameId);

      resourcesView.displayResourceMarket(currentGameId);
      playerView.displayPlayersAttributes(currentGame.getPlayers());

      // Build Phase
      buildingPhase.initiate(currentGameId);

      playerView.displayPlayersAttributes(currentGame.getPlayers());

      // Bureaucracy Phase
      bureaucracyPhase.initiate(currentGameId);

      currentGame.setRound(currentGame.getRound() + 1);
    }
  }

  private boolean endGame(State currentGame) {
    Iterator itr = currentGame.getPlayers().iterator();
    while (itr.hasNext()) {
      Player p = (Player) itr.next();
      if (p.getCities().size() >= rulesService.getEndGameTrigger(currentGame.getPlayers().size())) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    SpringApplication.run(GameEngine.class, args);
  }

}