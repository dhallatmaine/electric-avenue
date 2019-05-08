package ea.resource;

import ea.api.ResourcePlaceRequest;
import ea.api.ResourcePurchaseRequest;
import ea.game.GameService;
import ea.resource.ResourceController;
import ea.resource.ResourceService;
import ea.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ResourceControllerTest {

    GameService gameService;
    ResourceService resourceService;
    ResourceController target;

    Game game;

    String gameId = "1";

    @Before
    public void setup() {
        gameService = mock(GameService.class);
        resourceService = mock(ResourceService.class);
        target = new ResourceController(gameService, resourceService);

        game = new Game();

        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));
    }

    @Test
    public void purchase() {
        // Arrange
        ResourcePurchaseRequest request = mock(ResourcePurchaseRequest.class);

        // Act
        target.purchase(gameId, request);

        // Assert
        verify(resourceService).validateResourcePurchase(game, request);
    }

    @Test
    public void place() {
        // Arrange
        ResourcePlaceRequest request = mock(ResourcePlaceRequest.class);

        // Act
        target.place(gameId, request);

        // Assert
        verify(resourceService).validateResourcePlace(game, request);
    }

}
