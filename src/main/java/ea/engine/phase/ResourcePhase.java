package ea.engine.phase;

import ea.data.Player;
import ea.data.ResourceEnum;
import ea.engine.GameState;
import ea.engine.State;
import ea.services.PlayerService;
import ea.services.ResourceService;
import ea.views.DefaultView;
import ea.views.PlayerView;
import ea.views.ResourcesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class ResourcePhase {

  private final ResourceService resourceService;
  private final PlayerService playerService;
  private final GameState gameState;

  private ResourcesView resourcesView;
  private PlayerView playerView;
  private DefaultView defaultView;

  @Autowired
  public ResourcePhase(GameState gameState, ResourceService resourceService, PlayerService playerService) {
    this.resourceService = resourceService;
    this.playerService = playerService;
    this.gameState = gameState;
    resourcesView = new ResourcesView(gameState);
    playerView = new PlayerView();
    defaultView = new DefaultView();
  }

  public void initiate(Integer gameId) {
    State state = gameState.getById(gameId);
    List<Player> players = state.getPlayers();
    state.setPhase("ResourcePhase");
    Iterator itr = players.iterator();
    while (itr.hasNext()) {
      Player p = (Player) itr.next();

      boolean done = false;
      while (!done) {
        resourcesView.displayResourceMarket(gameId);

        playerView.displayPlayerAttributes(p);

        int choice = resourcesView.getResourceTypeFromUser();

        ResourceEnum userChoice;
        switch (choice) {
          case 1:
            userChoice = ResourceEnum.COAL;
            break;
          case 2:
            userChoice = ResourceEnum.OIL;
            break;
          case 3:
            userChoice = ResourceEnum.TRASH;
            break;
          case 4:
            userChoice = ResourceEnum.URANIUM;
            break;
          default:
            userChoice = ResourceEnum.GREEN;
        }

        if (ResourceEnum.GREEN.equals(choice)) {
          defaultView.outln(p.getName() + " has decided to skip purchasing resources for this round.");
          continue;
        }

        // get resources in market for that type
        List<Integer> resourceList = state.getResources().get(userChoice);

        // prompt user for how many
        Scanner scan = new Scanner(System.in);
        int amount = 0;
        boolean invalidInput = true;
        while (invalidInput) {
          int resources = playerService.getMaxResourcesAllowedForPurchase(p, userChoice);
          defaultView.outln("You can purchase up to " + resources + " " + userChoice.name());
          defaultView.outln("How much " + userChoice.name() + " would you like to purchase?");
          if (scan.hasNextInt()) {
            amount = scan.nextInt();

            if (amount <= resources) {
              invalidInput = false;
            } else {
              defaultView.outln("Invalid selection, try again.");
            }
          } else {
            defaultView.outln("Invalid selection, try again.");
            scan.next();
          }
        }

        // display price and confirm
        int price = resourceService.getPrice(resourceList, amount);

        defaultView.outln("Are you sure you want to purchase " + price + " elektro worth of " + userChoice.name() + "? [n/y]");

        String confirm = scan.next();

        if (confirm.equals("y")) {
          // purchase
          state.getResources().put(userChoice, resourceService.removeFromMarket(resourceList, amount));
          // add to player market
          playerService.addToPlayerResources(p, userChoice, amount);

          // remove money from player
          playerService.subtractMoneyFromPlayer(p, price);

          playerView.displayPlayerAttributes(p);

        }

        defaultView.outln("Would you like to purchase more resources? [n/y]");

        confirm = scan.next();

        if (confirm.equals("n")) {
          done = true;
        }

      }
    }
  }

}