package ea.engine.phase.impl;

import ea.data.Player;
import ea.data.Resource;
import ea.engine.GameState;
import ea.services.impl.PlayerService;
import ea.services.impl.ResourceServiceImpl;
import ea.views.BureaucracyPhaseView;
import ea.views.DefaultView;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BureaucracyPhase extends BasePhaseImpl {

  private PlayerService playerService;
  private DefaultView defaultView;
  private BureaucracyPhaseView bureaucracyPhaseView;

  public BureaucracyPhase(GameState gameState, PlayerService playerService) {
    super(gameState);
    gameState.setPhase(this);
    this.playerService = playerService;
    defaultView = new DefaultView();
    bureaucracyPhaseView = new BureaucracyPhaseView(new ResourceServiceImpl());
  }


  public void initiate(List<Player> players) {
    Iterator playersItr = players.iterator();
    while (playersItr.hasNext()) {
      Player p = (Player) playersItr.next();

      List<Resource> coal = p.getCoal();
      List<Resource> oil = p.getOil();
      List<Resource> trash = p.getTrash();
      List<Resource> uranium = p.getUranium();


      Scanner scan = new Scanner(System.in);
      if (coal.size() > 0) {
        int amount = bureaucracyPhaseView.getResourceAmountFromUser(Resource.SCOAL, coal.size());
        playerService.removeFromPlayerResources(p, coal, amount);
      }
      if (oil.size() > 0) {
        int amount = bureaucracyPhaseView.getResourceAmountFromUser(Resource.SOIL, oil.size());
        playerService.removeFromPlayerResources(p, oil, amount);
      }
      if (trash.size() > 0) {
        int amount = bureaucracyPhaseView.getResourceAmountFromUser(Resource.STRASH, trash.size());
        playerService.removeFromPlayerResources(p, trash, amount);
      }
      if (uranium.size() > 0) {
        int amount = bureaucracyPhaseView.getResourceAmountFromUser(Resource.SURANIUM, uranium.size());
        playerService.removeFromPlayerResources(p, uranium, amount);
      }
    }
  }

}