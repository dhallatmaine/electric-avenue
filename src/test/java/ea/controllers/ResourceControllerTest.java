package ea.controllers;

import ea.api.ResourcePlaceRequest;
import ea.api.ResourcePurchaseRequest;
import ea.services.GameService;
import ea.services.ResourceService;
import ea.state.State;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResourceControllerTest {

    GameService gameService;
    ResourceService resourceService;
    ResourceController target;

    State game;

    @Before
    public void setup() {
        gameService = mock(GameService.class);
        resourceService = mock(ResourceService.class);
        target = new ResourceController(gameService, resourceService);

        game = new State();

        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));
    }

    @Test
    public void purchase() {
        // Arrange
        ResourcePurchaseRequest request = mock(ResourcePurchaseRequest.class);

        // Act
        target.purchase(1, request);

        // Assert
        verify(resourceService).validateResourcePurchase(game, request);
    }

    @Test
    public void place() {
        // Arrange
        ResourcePlaceRequest request = mock(ResourcePlaceRequest.class);

        // Act
        target.place(1, request);

        // Assert
        verify(resourceService).validateResourcePlace(game, request);
    }

}
