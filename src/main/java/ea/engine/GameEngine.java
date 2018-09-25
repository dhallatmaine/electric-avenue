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
    resourceService.initializeResources();
    powerPlantsView = new PowerPlantsView();
    playerView = new PlayerView();
    resourcesView = new ResourcesView(gameState);
    defaultView = new DefaultView();
    powerPlantPhase = new PowerPlantPhase(gameState, powerPlantService, playerService);
    resourcePhase = new ResourcePhase(gameState, resourceService, playerService);
    buildingPhase = new BuildingPhase(gameState, america, playerService);

    gameState.setRound(1);

    america.initializeCities();

    gameState.setDeckPlants(powerPlantService.createInitialPowerPlants());
    powerPlantService.setupMarket();
    powerPlantService.shuffleDeck(true);

    playerService.setupPlayers();

    powerPlantsView.displayCurrentMarketPlants(gameState.getCurrentMarketPlants());
    powerPlantsView.displayFutureMarketPlants(gameState.getFutureMarketPlants());
    powerPlantsView.displayDeckPlants(gameState.getDeckPlants());

    while (!endGame()) {
      defaultView.outln("ROUND: " + gameState.getRound());
      playerView.displayPlayersAttributes(gameState.getPlayers());
      resourcesView.displayResourceMarket();

      playerView.displayPlayersCities(gameState.getPlayers());

      // Power Plant Phase
      powerPlantPhase.initiate(gameState.getPlayers());

      powerPlantsView.displayCurrentMarketPlants(gameState.getCurrentMarketPlants());
      powerPlantsView.displayFutureMarketPlants(gameState.getFutureMarketPlants());
      powerPlantsView.displayDeckPlants(gameState.getDeckPlants());
      playerView.displayPlayersAttributes(gameState.getPlayers());

      // Resource Phase
      resourcePhase.initiate(gameState.getPlayers());

      resourcesView.displayResourceMarket();
      playerView.displayPlayersAttributes(gameState.getPlayers());

      // Build Phase
      buildingPhase.initiate(gameState.getPlayers());

      playerView.displayPlayersAttributes(gameState.getPlayers());

      // Bureaucracy Phase
      bureaucracyPhase.initiate(gameState.getPlayers());

      gameState.setRound(gameState.getRound() + 1);
    }
  }

  private boolean endGame() {
    Iterator itr = gameState.getPlayers().iterator();
    while (itr.hasNext()) {
      Player p = (Player) itr.next();
      if (p.getCities().size() >= rulesService.getEndGameTrigger(gameState.getPlayers().size())) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    SpringApplication.run(GameEngine.class, args);
  }

}