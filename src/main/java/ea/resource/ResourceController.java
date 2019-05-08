package ea.resource;

import ea.api.ResourcePlaceRequest;
import ea.api.ResourcePurchaseRequest;
import ea.game.GameNotFoundException;
import ea.game.GameService;
import ea.resource.ResourceService;
import ea.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

    private final GameService gameService;
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(GameService gameService, ResourceService resourceService) {
        this.gameService = gameService;
        this.resourceService = resourceService;
    }

    @PostMapping
    @RequestMapping("/resource/{gameId}")
    public void purchase(
            @PathVariable("gameId") String gameId,
            @RequestBody ResourcePurchaseRequest purchaseRequest) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        resourceService.validateResourcePurchase(game, purchaseRequest);
    }

    @PostMapping
    @RequestMapping("/resource/place/{gameId}")
    public void place(
            @PathVariable("gameId") String gameId,
            @RequestBody ResourcePlaceRequest resourcePlaceRequest) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        resourceService.validateResourcePlace(game, resourcePlaceRequest);
    }

}
