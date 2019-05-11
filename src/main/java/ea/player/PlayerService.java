package ea.player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ea.powerplant.PowerPlant;
import ea.resource.Resource;
import ea.powerplant.PowerPlantService;
import ea.rules.BaseRules;
import ea.game.Game;
import ea.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class PlayerService {

    private PowerPlantService powerPlantService;

    @Autowired
    public PlayerService(PowerPlantService powerPlantService) {
        this.powerPlantService = powerPlantService;
    }

    public List<Player> setupPlayers() {
        Player player1 = new Player()
                .withUserId("1")
                .withColor(Color.BLACK)
                .withMoney(BaseRules.START_MONEY)
                .withCities(new LinkedList<>())
                .withPowerPlants(new LinkedList<>())
                .withResources(new HashMap<>());

        Player player2 = new Player()
                .withUserId("2")
                .withColor(Color.BLUE)
                .withMoney(BaseRules.START_MONEY)
                .withCities(new LinkedList<>())
                .withPowerPlants(new LinkedList<>())
                .withResources(new HashMap<>());

        Player player3 = new Player()
                .withUserId("3")
                .withColor(Color.GREEN)
                .withMoney(BaseRules.START_MONEY)
                .withCities(new LinkedList<>())
                .withPowerPlants(new LinkedList<>())
                .withResources(new HashMap<>());

        return ImmutableList.of(player1, player2, player3);
    }

    public List<Player> setupPlayers(List<User> users) {
        List<Color> colors = Arrays.asList(Color.values());
        Collections.shuffle(colors);

        return IntStream.range(0, users.size())
                .mapToObj(i -> new Player()
                        .withUserId(users.get(i).getId())
                        .withColor(colors.get(i))
                        .withMoney(BaseRules.START_MONEY)
                        .withCities(new LinkedList<>())
                        .withPowerPlants(new LinkedList<>())
                        .withResources(new HashMap<>()))
                .collect(Collectors.toList());
    }

    public int getMaxResourcesAllowedForPurchase(Player player, Resource resource) {
        int capacity = player.getPowerPlants().stream()
                .filter(plant -> plant.getResources().contains(resource))
                .map(PowerPlant::getResourceCapacity)
                .mapToInt(Integer::intValue)
                .sum();

        int held = Optional.ofNullable(player.getResources())
                .orElse(ImmutableMap.of())
                .values().stream()
                .filter(resourceEnums -> resourceEnums.contains(resource))
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();

        return capacity - held;
    }

    public void addToPlayerResources(
            Player player,
            Resource type,
            int amountToAdd) {

        IntStream.range(0, amountToAdd)
                .forEach(i ->
                        player.getResources().entrySet().stream()
                                .forEach(entry -> {
                                    PowerPlant plant = player.getPowerPlants().stream()
                                            .filter(p -> p.getValue().equals(entry.getKey()))
                                            .findFirst()
                                            .orElse(null);
                                    if (!plant.getResourceCapacity().equals(entry.getValue().size())
                                            && plant.getResources().contains(type)) {
                                        entry.getValue().add(type);
                                    }
                                })
                );
    }

    public Player findPlayerByColor(Game game, Color color) {
        return game.getPlayers().stream()
                .filter(player -> player.getColor().equals(color))
                .findFirst()
                .get();
    }

    public Player capturePlant(
            Game game,
            PowerPlant plant,
            Player player,
            Integer price,
            Optional<PowerPlant> plantToRemove) {
        Player playerWithPlant = addPlantToPlayer(player, plant, plantToRemove);
        Player playerWithMoney = subtractMoneyFromPlayer(playerWithPlant, price);
        powerPlantService.flipNewCard(game, plant);
        return playerWithMoney;
    }

    private Player addPlantToPlayer(Player player, PowerPlant plant, Optional<PowerPlant> plantToRemove) {
        plantToRemove.ifPresent(remove ->
                player.withPowerPlants(
                        player.getPowerPlants().stream()
                                .filter(p -> !p.getValue().equals(plantToRemove.get().getValue()))
                                .collect(Collectors.toList())));

        return player
                .withPowerPlants(
                        Stream.concat(player.getPowerPlants().stream(), Stream.of(plant))
                                .collect(Collectors.toList()));
    }

    private Player subtractMoneyFromPlayer(Player player, int amount) {
        int money = player.getMoney();
        money = money - amount;
        return player.withMoney(money);
    }
}