package ea.services;

import ea.api.*;
import ea.data.*;
import ea.state.AuctionRound;
import ea.state.BidRound;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BidService {

    private final PlayerService playerService;
    private final PowerPlantService powerPlantService;
    private final TurnOrderService turnOrderService;

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
        bidRound.getAuctionRounds().putIfAbsent(plant, auction);

        game.getBidRounds().put(round, bidRound);

        return new BidResponse()
                .withPlant(plant)
                .withAuctionStarted(true)
                .withOrder(game.getTurnOrder())
                .withPlayerToStartAuction(auctionStartingPayer);
    }

    public BidResponse pass(State game, PassRequest bid) {
        BidRound bidRound = game.getBidRounds().getOrDefault(
                game.getRound(),
                new BidRound()
                        .withBidOrder(game.getTurnOrder()));
        List<Color> order = bidRound.getBidOrder().stream()
                .filter(color -> !color.equals(bid.getPlayer()))
                .collect(Collectors.toList());
        bidRound.withBidOrder(order);

        game.getBidRounds().put(game.getRound(), bidRound);

        return new BidResponse()
                .withAuctionStarted(false)
                .withOrder(order)
                .withPhaseOver(order.size() == 0);
    }

    public PlantCaptureResponse capture(State game, PlantCaptureRequest captureRequest) {
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                captureRequest.getPlant());

        Player player = playerService.findPlayerByColor(game, captureRequest.getPlayer());

        Optional<PowerPlant> plantToRemove = player.getPowerPlants().stream()
                .filter(p -> p.getValue().equals(captureRequest.getPlantToRemove()))
                .findFirst();

        Integer price = game.getBidRounds().get(game.getRound()).getAuctionRounds().get(plant).getBid();

        Player withPlant = playerService.capturePlant(game, plant, player, price, plantToRemove);

        return new PlantCaptureResponse()
                .withPlant(plant)
                .withPlayer(withPlant)
                .withPlantRemoved(plantToRemove.orElse(null));
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

        BidRound bidRound = game.getBidRounds().getOrDefault(
                game.getRound(),
                new BidRound()
                        .withBidOrder(game.getTurnOrder()));
        if (!bidRound.getBidOrder().contains(bidRequest.getPlayer()))
            throw new RuntimeException("Player is not eligible to bid");
    }

    public void validatePass(State game) {
        if (game.getRound().equals(0))
            throw new RuntimeException("You must purchase a plant during the first round of the game");
    }

}
