package ea.services;

import ea.data.Color;
import ea.data.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TurnOrderService {

    private ShuffleService shuffleService;

    @Autowired
    public TurnOrderService(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    public List<Color> determineInitialTurnOrder(List<Player> players) {
        List<Player> random = shuffleService.shuffle(players);
        return random.stream()
                .map(Player::getColor)
                .collect(Collectors.toList());
    }

}
