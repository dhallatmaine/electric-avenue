package ea.services;

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

    public AuctionResponse auction(State game, BidRequest bidRequest) {
        int round = game.getRound();
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        AuctionRound auctionRound = game.getBidRounds().get(round).getAuctionRounds().get(plant);

        Color nextBidder = turnOrderService.getNextPlayer(
                auctionRound.getAuctionOrder(),
                bidRequest.getPlayer());

        auctionRound
                .withHighBidder(bidRequest.getPlayer())
                .withBid(bidRequest.getBidAmount());

        return new AuctionResponse()
                .withHighBid(bidRequest.getBidAmount())
                .withHighBidder(bidRequest.getPlayer())
                .withPlant(plant)
                .withOrder(auctionRound.getAuctionOrder())
                .withNextBidder(nextBidder);
    }

    public AuctionResponse pass(State game, PassRequest pass, Integer plantValue) {
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                plantValue);

        AuctionRound auctionRound = game.getBidRounds().get(game.getRound())
                .getAuctionRounds().get(plant);
        List<Color> order = auctionRound.getAuctionOrder().stream()
                .filter(color -> !color.equals(pass.getPlayer()))
                .collect(Collectors.toList());

        Color nextBidder = turnOrderService.getNextPlayer(
                auctionRound.getAuctionOrder(),
                pass.getPlayer());

        auctionRound.withAuctionOrder(order).withAuctionFinished(order.size() == 1);

        return new AuctionResponse()
                .withHighBid(auctionRound.getBid())
                .withHighBidder(auctionRound.getHighBidder())
                .withPlant(plant)
                .withOrder(order)
                .withNextBidder(auctionRound.getAuctionFinished() ? null : nextBidder)
                .withAuctionFinished(auctionRound.getAuctionFinished());
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

        if (!game.getBidRounds().get(game.getRound()).getBidOrder().contains(bidRequest.getPlayer()))
            throw new RuntimeException("Player is not eligible to bid");
    }

    public void validateAuction(State game, BidRequest bidRequest) {
        Player player = playerService.findPlayerByColor(game, bidRequest.getPlayer());
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(
                game.getCurrentMarketPlants(),
                bidRequest.getPlantValue());

        if (bidRequest.getBidAmount() > player.getMoney())
            throw new RuntimeException("Insufficient funds");

        if (bidRequest.getBidAmount() <= game.getBidRounds().get(game.getRound())
                .getAuctionRounds().get(plant).getBid())
            throw new RuntimeException("Bid must be greater than current high bid");

        if (!game.getBidRounds().get(game.getRound())
                .getAuctionRounds().get(plant).getAuctionOrder().contains(bidRequest.getPlayer()))
            throw new RuntimeException("Player is not eligible to bid");
    }

}
