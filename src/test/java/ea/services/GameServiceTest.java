package ea.services;

import ea.state.MongoGameDataStore;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    MongoGameDataStore gameDataStore;
    PlayerService playerService;
    PowerPlantService powerPlantService;
    TurnOrderService turnOrderService;
    GameService target;

    @Before
    public void setup() {
        gameDataStore = mock(MongoGameDataStore.class);
        playerService = mock(PlayerService.class);
        powerPlantService = mock(PowerPlantService.class);
        turnOrderService = mock(TurnOrderService.class);
        target = new GameService(gameDataStore, playerService, powerPlantService, turnOrderService);
    }

    @Test
    public void createGame() {
        // Act
        target.createGame();

        // Assert
        verify(powerPlantService, times(1)).createInitialPowerPlants();
        verify(powerPlantService, times(1)).setupCurrentMarket(any());
        verify(powerPlantService, times(1)).setupFutureMarket(any());
        verify(powerPlantService, times(1)).shuffleDeck(any(), anyBoolean());
        verify(playerService, times(1)).setupPlayers();
        verify(turnOrderService, times(1)).determineInitialTurnOrder(any());
        verify(gameDataStore, times(1)).create(any(), any(), any(), any(), any(), any(), any());
    }

}
