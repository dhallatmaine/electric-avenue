package ea.services;


import ea.api.ResourcePlaceRequest;
import ea.api.ResourcePurchaseRequest;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.state.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ResourceService {

    private final PlayerService playerService;
    private final GameService gameService;

    @Autowired
    public ResourceService(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
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

    public void purchaseResources(Game game, ResourcePurchaseRequest purchaseRequest) {
        Player player = playerService.findPlayerByColor(game, purchaseRequest.getPlayer());

        List<Integer> price = new ArrayList<>();
        purchaseRequest.getResources().entrySet().forEach(entry -> {
            List<Integer> newMarket = removeFromMarket(game.getResources().get(entry.getKey().name()), entry.getValue());
            gameService.setResourceMarket(game, entry.getKey(), newMarket);
            price.add(getPrice(game.getResources().get(entry.getKey().name()), entry.getValue()));
        });

        Integer totalPrice = price.stream().mapToInt(Integer::intValue).sum();
        player.withMoney(player.getMoney() - totalPrice);
    }

    public void placeResources(Game game, ResourcePlaceRequest placeRequest) {
        Player player = playerService.findPlayerByColor(game, placeRequest.getPlayer());

        placeRequest.getResourcesToPlace().entrySet().forEach(entry -> {
            PowerPlant plant = player.getPowerPlants().stream()
                    .filter(p -> p.getValue().equals(entry.getKey()))
                    .findFirst()
                    .get();
            List<Resource> newResources =
                    Stream.concat(player.getResources().get(plant.getValue()).stream(), entry.getValue().stream())
                            .collect(Collectors.toList());
            gameService.setPlayerResources(newResources, player, plant);
        });
    }

    public void validateResourcePurchase(Game game, ResourcePurchaseRequest purchaseRequest) {
        Player player = playerService.findPlayerByColor(game, purchaseRequest.getPlayer());

        List<Integer> price = new ArrayList<>();
        purchaseRequest.getResources().entrySet().forEach(entry -> {
            price.add(getPrice(game.getResources().get(entry.getKey().name()), entry.getValue()));
            int max = playerService.getMaxResourcesAllowedForPurchase(player, entry.getKey());
            if (entry.getValue() > max)
                throw new RuntimeException("Can not purchase this many " + entry.getKey() + " resources");
        });

        Integer totalPrice = price.stream().mapToInt(Integer::intValue).sum();
        if (totalPrice > player.getMoney())
            throw new RuntimeException("Insufficient funds");
    }

    // must have room available on plant
    public void validateResourcePlace(Game game, ResourcePlaceRequest placeRequest) {
        Player player = playerService.findPlayerByColor(game, placeRequest.getPlayer());
        Map<Integer, PowerPlant> playerPlants = player.getPowerPlants().stream()
                .collect(Collectors.toMap(PowerPlant::getValue, Function.identity()));

        placeRequest.getResourcesToPlace().entrySet().forEach(entry -> {
            PowerPlant plant = playerPlants.get(entry.getKey());

            Set<Resource> resources = new HashSet<>(entry.getValue());
            if (!resources.containsAll(plant.getResources()))
                throw new RuntimeException("This plant does not allow this resource type");

            if (plant.getResourceCapacity() - player.getResources().get(plant.getValue()).size() < entry.getValue().size())
                throw new RuntimeException("Not enough room on this plant to place these resources");
        });
    }

}