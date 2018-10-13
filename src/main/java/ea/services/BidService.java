package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.*;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BidService {

    private PlayerService playerService;
    private PowerPlantService powerPlantService;

    @Autowired
    public BidService(PlayerService playerService, PowerPlantService powerPlantService) {
        this.playerService = playerService;
        this.powerPlantService = powerPlantService;
    }

    public BidResponse createBid(
            State game,
            BidRequest bid) {
        Player player = playerService.findPlayerByColor(game, bid.getPlayer());
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(game.getDeckPlants(), bid.getPlantValue());

        if (bid.equals(0)) {
            if (game.getBidOrder().size() > 2) {
                int currentPlayerIndex = IntStream.range(0, game.getBidOrder().size())
                        .filter(i -> bid.getPlayer().equals(game.getBidOrder().get(i)))
                        .findFirst()
                        .getAsInt();
                Color nextPlayer = (currentPlayerIndex == (game.getBidOrder().size() - 1)) ?
                        game.getBidOrder().get(0) : game.getBidOrder().get(currentPlayerIndex + 1);
                game.withBidOrder(game.getBidOrder().stream()
                        .filter(color -> !color.equals(bid.getPlayer()))
                        .collect(Collectors.toList()));
                return new BidResponse().withBid(bid).withBiddingEnded(false).withNextPlayer(nextPlayer);
            } else {
                capturePlant(game, plant, player, bid.getPlantToRemoveValue());
                return new BidResponse().withBid(bid).withBiddingEnded(true);
            }
        }
        throw new RuntimeException("Unexpected branch reached during bidding");
    }

    public void validateBid(State game, BidRequest bid) {
        Player player = playerService.findPlayerByColor(game, bid.getPlayer());

        if (bid.getBidAmount() > player.getMoney())
            throw new RuntimeException("Insufficient funds");

        if (bid.getBidAmount() < game.getCurrentBid() && !bid.equals(0))
            throw new RuntimeException("Bid is less than current bid");
    }

    private void capturePlant(State game, PowerPlant plant, Player player, Integer plantToRemoveValue) {
        Optional<PowerPlant> plantToRemove = player.getPowerPlants().stream()
                .filter(p -> p.getValue().equals(plantToRemoveValue))
                .findFirst();
        playerService.addPlantToPlayer(player, plant, plantToRemove);
        playerService.subtractMoneyFromPlayer(player, game.getCurrentBid());

        powerPlantService.flipNewCard(game, plant);

        game.withCurrentBid(0);
        game.withBidOrder(ImmutableList.of());
    }

}
