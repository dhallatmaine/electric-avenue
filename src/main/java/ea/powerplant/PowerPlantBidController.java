package ea.powerplant;

import ea.api.*;
import ea.game.GameNotFoundException;
import ea.powerplant.AuctionService;
import ea.powerplant.BidService;
import ea.game.GameService;
import ea.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping
    @RequestMapping("/bid/{gameId}")
    public BidResponse bid(
            @PathVariable("gameId") String gameId,
            @RequestBody BidRequest bid) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        bidService.validateBid(game, bid);
        return bidService.bid(game, bid);
    }

    @PostMapping
    @RequestMapping("/bid/pass/{gameId}")
    public BidResponse pass(
            @PathVariable("gameId") String gameId,
            @RequestBody PassRequest pass) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        bidService.validatePass(game);
        return bidService.pass(game, pass);
    }

    @PostMapping
    @RequestMapping("/auction/{gameId}")
    public AuctionResponse auction(
            @PathVariable("gameId") String gameId,
            @RequestBody BidRequest bid) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        auctionService.validateAuction(game, bid);
        return auctionService.auction(game, bid);
    }

    @PostMapping
    @RequestMapping("/auction/pass/{gameId}/{plant}")
    public AuctionResponse auctionPass(
            @PathVariable("gameId") String gameId,
            @PathVariable("plant") Integer plant,
            @RequestBody PassRequest pass) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        return auctionService.pass(game, pass, plant);
    }


    @PostMapping
    @RequestMapping("/capture/{gameId}")
    public PlantCaptureResponse capture(
            @PathVariable("gameId") String gameId,
            @RequestBody PlantCaptureRequest captureRequest) {
        Game game = gameService.getGame(gameId)
                .orElseThrow(GameNotFoundException::new);

        return bidService.capture(game, captureRequest);
    }

}
