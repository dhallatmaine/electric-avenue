package ea.services;


import ea.api.ResourcePurchaseRequest;
import ea.data.Player;
import ea.data.Resource;
import ea.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ResourceService {

    private final PlayerService playerService;

    @Autowired
    public ResourceService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Integer getPrice(List<Integer> resources, int amount) {
        return resources.stream()
                .filter(i -> i > 0)
                .limit(amount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<Integer> removeFromMarket(List<Integer> resources, int amount) {
        Stream<Integer> emptySpaces = resources.stream()
                .filter(i -> i == 0);

        Stream<Integer> removed = Stream.iterate(0, i -> 0)
                .limit(amount);

        Stream<Integer> newEmptyArea = Stream.concat(emptySpaces, removed);

        Stream<Integer> remaining = resources.stream()
                .filter(i -> i > 0)
                .skip(amount);

        return Stream.concat(newEmptyArea, remaining).collect(Collectors.toList());
    }

    public void validateResourcePurchase(State game, ResourcePurchaseRequest purchaseRequest) {
        Player player = playerService.findPlayerByColor(game, purchaseRequest.getPlayer());

        purchaseRequest.getResources().entrySet().forEach(entry -> {
            int max = playerService.getMaxResourcesAllowedForPurchase(player, entry.getKey());
            if (entry.getValue() > max)
                throw new RuntimeException("Can not purchase this many " + entry.getKey() + " resources");
        });
    }

}