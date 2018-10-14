package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.*;
import ea.state.AuctionRound;
import ea.state.BidRound;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            bidRound.withAuctionRounds(Stream.concat(Stream.of(auction), bidRound.getAuctionRounds().stream())
                    .collect(Collectors.toList()));

            return new BidResponse()
                    .withPlant(plant)
                    .withAuctionStarted(true);
        } else {
            List<Color> order = bidRound.getBidOrder().stream()
                    .filter(color -> !color.equals(bidRequest.getPlayer()))
                    .collect(Collectors.toList());
            bidRound.withBidOrder(order);
            return new BidResponse()
                    .withAuctionStarted(false);
        }
    }

    public AuctionResponse auction(State game, AuctionRequest auctionRequest) {
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
