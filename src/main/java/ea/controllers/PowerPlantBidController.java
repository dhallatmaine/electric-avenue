package ea.controllers;

import ea.data.BidRequest;
import ea.data.BidResponse;
import ea.exceptions.GameNotFoundException;
import ea.services.GameService;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class PowerPlantBidController {

    private GameService gameService;

    @Autowired
    public PowerPlantBidController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{id}")
    public BidResponse bid(
            @PathVariable("id") Integer gameId,
            @RequestBody BidRequest bid) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        return new BidResponse()
                .withBid(bid)
                .withNextPlayer(game.getTurnOrder().get(0));
    }

}
