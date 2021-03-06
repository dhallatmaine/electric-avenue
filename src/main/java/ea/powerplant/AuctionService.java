package ea.powerplant;

import ea.api.AuctionResponse;
import ea.api.BidRequest;
import ea.api.PassRequest;
import ea.player.Color;
import ea.player.Player;
import ea.game.GameService;
import ea.game.AuctionRound;
import ea.game.BidRound;
import ea.game.Game;
import ea.player.PlayerService;
import ea.turnorder.TurnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionService {

    private final PowerPlantService powerPlantService;
    private final TurnOrderService turnOrderService;
    private final PlayerService playerService;
    private final GameService gameService;

    @Autowired
    public AuctionService(
            PowerPlantService powerPlantService,
            TurnOrderService turnOrderService,
            PlayerService playerService,
            GameService gameService) {
        this.powerPlantService = powerPlantService;
        this.turnOrderService = turnOrderService;
        this.playerService = playerService;
        this.gameService = gameService;
    }

    public AuctionResponse auction(Game game, BidRequest bidRequest) {
        int round = game.getRound();
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        AuctionRound auctionRound = game.getBidRounds().get(round).getAuctionRounds().get(plant.getValue());

        Color nextBidder = turnOrderService.getNextPlayer(
                auctionRound.getAuctionOrder(),
                bidRequest.getPlayer());

        auctionRound
                .withHighBidder(bidRequest.getPlayer())
                .withBid(bidRequest.getBidAmount());

        gameService.save(game);

        return new AuctionResponse()
                .withHighBid(bidRequest.getBidAmount())
                .withHighBidder(bidRequest.getPlayer())
                .withPlant(plant)
                .withOrder(auctionRound.getAuctionOrder())
                .withNextBidder(nextBidder);
    }

    public AuctionResponse pass(Game game, PassRequest pass, Integer plantValue) {
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                plantValue);

        BidRound bidRound = game.getBidRounds().get(game.getRound());
        AuctionRound auctionRound = bidRound.getAuctionRounds().get(plant.getValue());
        List<Color> order = auctionRound.getAuctionOrder().stream()
                .filter(color -> !color.equals(pass.getPlayer()))
                .collect(Collectors.toList());

        Color nextBidder = turnOrderService.getNextPlayer(
                auctionRound.getAuctionOrder(),
                pass.getPlayer());

        auctionRound
                .withAuctionOrder(order)
                .withAuctionFinished(order.size() == 1);

        if (auctionRound.getAuctionFinished()) {
            List<Color> bidOrder = bidRound.getBidOrder().stream()
                    .filter(color -> !color.equals(auctionRound.getHighBidder()))
                    .collect(Collectors.toList());
            bidRound.withBidOrder(bidOrder);
        }

        gameService.save(game);

        return new AuctionResponse()
                .withHighBid(auctionRound.getBid())
                .withHighBidder(auctionRound.getHighBidder())
                .withPlant(plant)
                .withOrder(order)
                .withNextBidder(auctionRound.getAuctionFinished() ? null : nextBidder)
                .withAuctionFinished(auctionRound.getAuctionFinished());
    }

    public void validateAuction(Game game, BidRequest bidRequest) {
        Player player = playerService.findPlayerByColor(game, bidRequest.getPlayer());
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        if (bidRequest.getBidAmount() > player.getMoney())
            throw new RuntimeException("Insufficient funds");

        if (bidRequest.getBidAmount() <= game.getBidRounds().get(game.getRound())
                .getAuctionRounds().get(plant.getValue()).getBid())
            throw new RuntimeException("Bid must be greater than current high bid");

        if (!game.getBidRounds().get(game.getRound())
                .getAuctionRounds().get(plant.getValue()).getAuctionOrder().contains(bidRequest.getPlayer()))
            throw new RuntimeException("Player is not eligible to bid");
    }

}
