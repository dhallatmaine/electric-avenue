package ea.engine.phase;

import ea.engine.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurnOrderPhase {

  private final GameState gameState;

  @Autowired
  public TurnOrderPhase(GameState gameState) {
    this.gameState = gameState;
  }

}