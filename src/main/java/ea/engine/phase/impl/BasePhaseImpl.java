package ea.engine.phase.impl;

import ea.engine.GameState;
import ea.engine.phase.BasePhase;

public class BasePhaseImpl implements BasePhase {

  private GameState gameState;

  public BasePhaseImpl(GameState gameState) {
    this.gameState = gameState;
  }

  public void initiate() { }

  public GameState getGameState() { return gameState; }
  public void setGameState(GameState gameState) { this.gameState = gameState; }

}