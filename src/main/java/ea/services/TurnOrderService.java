package ea.services;

import ea.data.Color;
import ea.data.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class TurnOrderService {

    private ShuffleService shuffleService;

    @Autowired
    public TurnOrderService(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    public List<Color> determineInitialTurnOrder(List<Player> players) {
        //List<Player> random = shuffleService.shuffle(players);
        List<Player> random = new ArrayList<>(players);
        return random.stream()
                .map(Player::getColor)
                .collect(Collectors.toList());
    }

    public Color getNextPlayer(List<Color> players, Color player) {
        int currentPlayerIndex = IntStream.range(0, players.size())
                .filter(i -> player.equals(players.get(i)))
                .findFirst()
                .getAsInt();
        return (currentPlayerIndex == (players.size() - 1)) ?
                players.get(0) : players.get(currentPlayerIndex + 1);
    }

    public List<Color> determineOrderStartingAtPlayer(List<Color> players, Color player) {
        List<Color> start = players.stream()
                .takeWhile(p -> !p.equals(player))
                .collect(Collectors.toList());

        List<Color> end = players.stream()
                .skip(start.size())
                .collect(Collectors.toList());

        return Stream.concat(end.stream(), start.stream())
                .collect(Collectors.toList());
    }

}
