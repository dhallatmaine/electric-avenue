package ea.engine.phase.impl;

import ea.data.Player;
import ea.engine.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurnOrderPhase {

  private final GameState gameState;

  @Autowired
  public TurnOrderPhase(GameState gameState) {
    this.gameState = gameState;
  }

  public void initiate(List<Player> players) {
    gameState.setPhase("TurnOrderPhase");
  }

}