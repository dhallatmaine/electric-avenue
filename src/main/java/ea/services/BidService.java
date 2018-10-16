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
        BidRound bidRound = game.getBidRounds().getOrDefault(
                round,
                new BidRound()
                        .withBidOrder(game.getTurnOrder()));

        if (bidRequest.getBidAmount() > 0) {
            bidRound.withPlantPurchased(true);

            PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                    game.getCurrentMarketPlants(),
                    bidRequest.getPlantValue());
            Color auctionStartingPayer = turnOrderService.getNextPlayer(
                    bidRound.getBidOrder(),
                    bidRequest.getPlayer());
            List<Color> auctionOrder = turnOrderService.determineOrderStartingAtPlayer(
                    bidRound.getBidOrder(),
                    auctionStartingPayer);

            AuctionRound auction = new AuctionRound()
                    .withBid(bidRequest.getBidAmount())
                    .withHighBidder(bidRequest.getPlayer())
                    .withPlant(plant)
                    .withAuctionOrder(auctionOrder);
            auction.getPlantToRemove().putIfAbsent(bidRequest.getPlayer(), bidRequest.getPlantValueToRemove());
            bidRound.getAuctionRounds().putIfAbsent(plant, auction);

            game.getBidRounds().put(round, bidRound);

            return new BidResponse()
                    .withPlant(plant)
                    .withAuctionStarted(true)
                    .withOrder(game.getTurnOrder())
                    .withPlayerToStartAuction(auctionStartingPayer);
        } else {
            List<Color> order = bidRound.getBidOrder().stream()
                    .filter(color -> !color.equals(bidRequest.getPlayer()))
                    .collect(Collectors.toList());
            bidRound.withBidOrder(order);

            game.getBidRounds().put(round, bidRound);

            return new BidResponse()
                    .withAuctionStarted(false)
                    .withOrder(order)
                    .withPhaseOver(order.size() == 0);
        }
    }

    public AuctionResponse auction(State game, BidRequest bidRequest) {
        int round = game.getRound();
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        AuctionRound auctionRound = game.getBidRounds().get(round).getAuctionRounds().get(plant);
        auctionRound.getPlantToRemove().putIfAbsent(bidRequest.getPlayer(), bidRequest.getPlantValueToRemove());

        Color nextBidder = turnOrderService.getNextPlayer(
                auctionRound.getAuctionOrder(),
                bidRequest.getPlayer());

        if (bidRequest.getBidAmount() > 0) {
            auctionRound
                    .withHighBidder(bidRequest.getPlayer())
                    .withBid(bidRequest.getBidAmount());

            return new AuctionResponse()
                    .withHighBid(bidRequest.getBidAmount())
                    .withHighBidder(bidRequest.getPlayer())
                    .withPlant(plant)
                    .withOrder(auctionRound.getAuctionOrder())
                    .withNextBidder(nextBidder);
        } else {
            List<Color> order = auctionRound.getAuctionOrder().stream()
                    .filter(color -> !color.equals(bidRequest.getPlayer()))
                    .collect(Collectors.toList());

            auctionRound.withAuctionOrder(order);

            if (order.size() == 1) {
                auctionRound.withAuctionFinished(true);
                playerService.capturePlant(
                        game,
                        plant,
                        auctionRound.getHighBidder(),
                        auctionRound.getPlantToRemove().get(auctionRound.getHighBidder()));
            }

            return new AuctionResponse()
                    .withHighBid(auctionRound.getBid())
                    .withHighBidder(auctionRound.getHighBidder())
                    .withPlant(plant)
                    .withOrder(order)
                    .withNextBidder(auctionRound.getAuctionFinished() ? null : nextBidder)
                    .withAuctionFinished(auctionRound.getAuctionFinished());
        }
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
