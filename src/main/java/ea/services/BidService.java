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
        Color nextPlayer = getNextPlayer(game, player.getColor());

        // player passed
        if (bid.getBidAmount().equals(0)) {
            //bidding continues
            if (game.getBidOrder().size() > 2) {
                game.withBidOrder(game.getBidOrder().stream()
                        .filter(color -> !color.equals(player.getColor()))
                        .collect(Collectors.toList()));
                return new BidResponse()
                        .withBid(bid)
                        .withBiddingEnded(false)
                        .withNextPlayer(nextPlayer)
                        .withCurrentBid(game.getCurrentBid());

            //bidding ends
            } else {
                PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(game.getDeckPlants(), bid.getPlantValue());
                game.withBidOrder(ImmutableList.of()).withCurrentBid(0);
                playerService.capturePlant(game, plant, player, bid.getPlantToRemoveValue());
                return new BidResponse()
                        .withBid(bid)
                        .withBiddingEnded(true)
                        .withNextPlayer(nextPlayer)
                        .withCurrentBid(game.getCurrentBid());
            }
        }

        game.withCurrentBid(bid.getBidAmount());
        return new BidResponse()
                .withBid(bid)
                .withBiddingEnded(false)
                .withNextPlayer(nextPlayer)
                .withCurrentBid(game.getCurrentBid());
    }

    public void validateBid(State game, BidRequest bid) {
        Player player = playerService.findPlayerByColor(game, bid.getPlayer());

        if (bid.getBidAmount() > player.getMoney())
            throw new RuntimeException("Insufficient funds");

        if (bid.getBidAmount() < game.getCurrentBid() && !bid.equals(0))
            throw new RuntimeException("Bid is less than current bid");
    }

    private Color getNextPlayer(State game, Color player) {
        int currentPlayerIndex = IntStream.range(0, game.getBidOrder().size())
                .filter(i -> player.equals(game.getBidOrder().get(i)))
                .findFirst()
                .getAsInt();
        return (currentPlayerIndex == (game.getBidOrder().size() - 1)) ?
                game.getBidOrder().get(0) : game.getBidOrder().get(currentPlayerIndex + 1);
    }

}
