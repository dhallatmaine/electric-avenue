package ea.engine.phase;

import ea.engine.GameState;
import ea.engine.State;
import ea.services.PlayerService;
import ea.services.PowerPlantService;
import ea.util.Bid;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.views.DefaultView;
import ea.views.PlayerView;
import ea.views.PowerPlantsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PowerPlantPhase {

  private final GameState gameState;
  private final PowerPlantService powerPlantService;
  private PlayerView playerView;
  private PlayerService playerService;
  private PowerPlantsView powerPlantsView;
  private DefaultView defaultView;

  @Autowired
  public PowerPlantPhase(GameState gameState, PowerPlantService powerPlantService, PlayerService playerService) {
    this.gameState = gameState;
    this.powerPlantService = powerPlantService;
    this.playerService = playerService;
    powerPlantsView = new PowerPlantsView();
    playerView = new PlayerView();
    defaultView = new DefaultView();
  }

  public void initiate(Integer gameId, List<Player> players) {
    State game = gameState.getById(gameId);
    game.setPhase("PowerPlantPhase");
    List<Player> playerList = new LinkedList<>(players);
    boolean bidding = true;
    while (bidding) {
      Iterator itr = playerList.iterator();
      Bid bid = null;
      while (itr.hasNext()) {
        Player p = (Player) itr.next();
        playerView.displayPlayerAttributes(p);
        bid = bidOnPlants(playerList, p, game.getCurrentMarketPlants(), game.getFutureMarketPlants());

        if (bid.getBid() > 0) {
          powerPlantService.flipNewCard(gameId, bid.getPowerPlant());
        }

        break;
      }

      playerList.remove(bid.getPlayer());

      if (playerList.isEmpty()) {
        bidding = false;
      }
    }

    defaultView.outln("End of the bidding phase");

  }

  private Bid bidOnPlants(List<Player> playersStillInAuction, Player player, List<PowerPlant> currentMarket, List<PowerPlant> futureMarket) {
    defaultView.outln("\nPlayer: \"" + player.getName() + "\", which Power Plant would you like to bid on?");

    powerPlantsView.displayCurrentMarketPlants(currentMarket);
    powerPlantsView.displayFutureMarketPlants(futureMarket);

    Scanner scan = new Scanner(System.in);
    int marketChoice = scan.nextInt();

    Bid auctionWinner = new Bid(player, 0, null);

    if (marketChoice == 0) {
      defaultView.outln("Player: \"" + player.getName() + "\" has decided to skip this round of bidding.");
      return auctionWinner;
    }

    PowerPlant chosenPlant = currentMarket.get(marketChoice - 1);


    int bid = 0;
    boolean invalidInput = true;
    while (invalidInput) {
      defaultView.outln("How much would you like to bid on Card - value: " + chosenPlant.getValue());
      if (scan.hasNextInt()) {
        bid = scan.nextInt();

        if (bid >= chosenPlant.getValue() && bid <= player.getMoney()) {
          invalidInput = false;
        } else {
          defaultView.outln("Invalid bid, please bid again.");
        }
      } else {
        defaultView.outln("Invalid bid, please bid again.");
        scan.next();
      }
    }

    List<Player> players = new LinkedList<Player>(playersStillInAuction);

    auctionWinner = auction(players, player, bid, chosenPlant);

    Player winningPlayer = auctionWinner.getPlayer();
    int winningBid = auctionWinner.getBid();

    playerService.subtractMoneyFromPlayer(winningPlayer, winningBid);

    List<PowerPlant> playersPowerPlants = player.getPowerPlants();
    swapPlants(playersPowerPlants, chosenPlant);
    winningPlayer.setPowerPlants(playersPowerPlants);

    return auctionWinner;
  }

  private void swapPlants(List<PowerPlant> playersPowerPlants, PowerPlant plant) {
    if (playersPowerPlants.size() >= 3) {
      defaultView.outln("Which plant would you like to remove?");
      powerPlantsView.displayPlayerPlants(playersPowerPlants);

      Scanner scan = new Scanner(System.in);
      int choice = scan.nextInt();

      PowerPlant discardPlant = playersPowerPlants.remove(choice);
      powerPlantService.getDiscardPlants().add(discardPlant);
    }
    playersPowerPlants.add(plant);
  }

  private Bid auction(List<Player> players, Player startPlayer, int currentBid, PowerPlant plant) {
    Bid bid = null;
    players.remove(startPlayer);
    boolean firstRun = true;
    while (true) {
      Iterator itr = players.iterator();
      List<Player> playersToRemove = new LinkedList<Player>();
      while (itr.hasNext()) {
        // special edge case for passing
        if (((players.size() - playersToRemove.size()) == 1) && !firstRun) {
          break;
        }


        Player p = (Player) itr.next();
        Scanner scan = new Scanner(System.in);

        int playerBid = 0;
        boolean invalidInput = true;
        while (invalidInput) {
          defaultView.outln("Player: \"" + p.getName() + "\", would you like to place a bid on Card - value: " + plant.getValue());
          defaultView.outln("Current Bid on Card - value: " + plant.getValue() + " is: " + currentBid);
          if (scan.hasNextInt()) {
            playerBid = scan.nextInt();

            // pass
            if (playerBid == 0) {
              break;
            }

            if (playerBid > currentBid && playerBid <= p.getMoney()) {
              invalidInput = false;
            } else {
              defaultView.outln("Invalid bid, please bid again.");
            }
          } else {
            defaultView.outln("Invalid bid, please bid again.");
            scan.next();
          }
        }

        // player passed
        if (playerBid == 0) {
          playersToRemove.add(p);
        }

        if (playerBid > currentBid) {
          bid = new Bid(p, playerBid, plant);
          currentBid = playerBid;
        }
      }

      if (!playersToRemove.isEmpty()) {
        players.removeAll(playersToRemove);
      }

      if (firstRun) {
        firstRun = false;
        players.add(0, startPlayer);
      }

      if (players.size() == 1) {
        return new Bid(players.get(0), currentBid, plant);
      }
    }
  }

}