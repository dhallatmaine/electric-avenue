package ea.controllers;

import ea.api.*;
import ea.exceptions.GameNotFoundException;
import ea.services.AuctionService;
import ea.services.BidService;
import ea.services.GameService;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PowerPlantBidController {

    private final GameService gameService;
    private final BidService bidService;
    private final AuctionService auctionService;

    @Autowired
    public PowerPlantBidController(
            GameService gameService,
            BidService bidService,
            AuctionService auctionService) {
        this.gameService = gameService;
        this.bidService = bidService;
        this.auctionService = auctionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/bid/{gameId}")
    public BidResponse bid(
            @PathVariable("gameId") Integer gameId,
            @RequestBody BidRequest bid) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        bidService.validateBid(game, bid);
        return bidService.bid(game, bid);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/bid/pass/{gameId}")
    public BidResponse pass(
            @PathVariable("gameId") Integer gameId,
            @RequestBody PassRequest pass) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        bidService.validatePass(game);
        return bidService.pass(game, pass);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/auction/{gameId}")
    public AuctionResponse auction(
            @PathVariable("gameId") Integer gameId,
            @RequestBody BidRequest bid) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        auctionService.validateAuction(game, bid);
        return auctionService.auction(game, bid);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/auction/pass/{gameId}/{plant}")
    public AuctionResponse auctionPass(
            @PathVariable("gameId") Integer gameId,
            @PathVariable("plant") Integer plant,
            @RequestBody PassRequest pass) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        return auctionService.pass(game, pass, plant);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/capture/{gameId}")
    public PlantCaptureResponse capture(
            @PathVariable("gameId") Integer gameId,
            @RequestBody PlantCaptureRequest captureRequest) {
        State game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        return bidService.capture(game, captureRequest);
    }

}
