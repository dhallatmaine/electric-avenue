package ea.services;

import ea.data.*;
import ea.state.AuctionRound;
import ea.state.BidRound;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BidService {

    private PlayerService playerService;
    private PowerPlantService powerPlantService;
    private TurnOrderService turnOrderService;

    @Autowired
    public BidService(
            PlayerService playerService,
            PowerPlantService powerPlantService,
            TurnOrderService turnOrderService) {
        this.playerService = playerService;
        this.powerPlantService = powerPlantService;
        this.turnOrderService = turnOrderService;
    }

    public BidResponse bid(State game, BidRequest bidRequest) {
        int round = game.getRound();
        BidRound bidRound = game.getBidRounds().get(round);
        if (bidRequest.getBidAmount() > 0) {
            bidRound.withPlantPurchased(true);

            PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                    game.getCurrentMarketPlants(),
                    bidRequest.getPlantValue());
            List<Color> auctionOrder = turnOrderService.determineOrderStartingAtPlayer(
                    bidRound.getBidOrder(),
                    bidRequest.getPlayer());

            AuctionRound auction = new AuctionRound()
                    .withBid(bidRequest.getBidAmount())
                    .withHighBidder(bidRequest.getPlayer())
                    .withPlant(plant)
                    .withAuctionOrder(auctionOrder);
            bidRound.getAuctionRounds().putIfAbsent(plant, auction);

            return new BidResponse()
                    .withPlant(plant)
                    .withAuctionStarted(true);
        } else {
            List<Color> order = bidRound.getBidOrder().stream()
                    .filter(color -> !color.equals(bidRequest.getPlayer()))
                    .collect(Collectors.toList());
            bidRound.withBidOrder(order);
            return new BidResponse()
                    .withAuctionStarted(false)
                    .withPhaseOver(order.size() == 0);
        }
    }

    public AuctionResponse auction(State game, AuctionRequest auctionRequest) {
        int round = game.getRound();
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                auctionRequest.getPlant());

        AuctionRound auctionRound = game.getBidRounds().get(round).getAuctionRounds().get(plant);

        if (auctionRequest.getBidAmount() > 0) {
            auctionRound
                    .withHighBidder(auctionRequest.getPlayer())
                    .withBid(auctionRequest.getBidAmount());


        }
        return new AuctionResponse();
    }

    public void validateBid(State game, BidRequest bidRequest) {
        Player player = playerService.findPlayerByColor(game, bidRequest.getPlayer());
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        if (bidRequest.getBidAmount() > player.getMoney())
            throw new RuntimeException("Insufficient funds");

        if (bidRequest.getBidAmount() < plant.getValue())
            throw new RuntimeException("Bid must be greater than or equal to the plant value");
    }

}
