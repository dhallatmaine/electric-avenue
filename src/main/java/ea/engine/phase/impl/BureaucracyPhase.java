package ea.engine.phase.impl;

import ea.data.Player;
import ea.data.Resource;
import ea.engine.GameState;
import ea.engine.State;
import ea.services.PlayerService;
import ea.views.BureaucracyPhaseView;
import ea.views.DefaultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class BureaucracyPhase {

  private final GameState gameState;
  private final PlayerService playerService;
  private final BureaucracyPhaseView bureaucracyPhaseView;
  private DefaultView defaultView;

  @Autowired
  public BureaucracyPhase(GameState gameState, PlayerService playerService, BureaucracyPhaseView bureaucracyPhaseView) {
    this.gameState = gameState;
    this.playerService = playerService;
    this.bureaucracyPhaseView = bureaucracyPhaseView;
    defaultView = new DefaultView();
  }

  public void initiate(Integer gameId) {
    State game = gameState.getById(gameId);
    game.setPhase("BureaucracyPhase");

    List<Player> players = game.getPlayers();

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