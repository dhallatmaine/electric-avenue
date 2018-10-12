package ea.services;

import ea.data.BidResponse;
import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

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
            Color playerColor,
            Integer plantValue,
            Integer plantToRemoveValue,
            Integer bid) {
        Player player = playerService.findPlayerByColor(game, playerColor);
        PowerPlant plant = powerPlantService.findPowerPlantInDeckByValue(game.getDeckPlants(), plantValue);

        if (bid.equals(0)) {
            game.withBidOrder(game.getBidOrder().stream()
                    .filter(color -> !color.equals(playerColor))
                    .collect(Collectors.toList()));
        }

        if (bid.compareTo(player.getMoney()) > 0) throw new RuntimeException("Insufficient funds");

        if (bid.compareTo(game.getCurrentBid()) > 0) throw new RuntimeException("Bid is less than current bid");

        PowerPlant plantToRemove = player.getPowerPlants().stream()
                .filter(p -> p.getValue().equals(plantToRemoveValue))
                .findFirst().get();
        playerService.addPlantToPlayer(player, plant, Optional.of(plantToRemove));
        return null;
    }

}
