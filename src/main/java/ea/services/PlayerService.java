package ea.services;

import com.google.common.collect.ImmutableMap;
import ea.data.*;
import ea.rules.BaseRules;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
public class PlayerService {

  public List<Player> setupPlayers() {
    List<Player> playerList = new LinkedList<>();

    Player player1 = new Player();
    player1.setMoney(BaseRules.START_MONEY);
    player1.setName("Player 1");
    List<City> cities = new LinkedList<>();
    player1.setCities(cities);
    player1.setPowerPlants(new LinkedList<>());
    player1.setResources(new HashMap<>());
    playerList.add(player1);

    Player player2 = new Player();
    player2.setMoney(BaseRules.START_MONEY);
    player2.setName("Player 2");
    player2.setCities(new LinkedList<>());
    player2.setPowerPlants(new LinkedList<>());
    player2.setResources(new HashMap<>());
    playerList.add(player2);

    Player player3 = new Player();
    player3.setMoney(BaseRules.START_MONEY);
    player3.setName("Player 3");
    player3.setCities(new LinkedList<>());
    player3.setPowerPlants(new LinkedList<>());
    player3.setResources(new HashMap<>());
    playerList.add(player3);

    return playerList;
  }

  public void subtractMoneyFromPlayer(Player player, int amount) {
    int money = player.getMoney();
    money = money - amount;
    player.setMoney(money);
  }

  public int getMaxResourcesAllowedForPurchase(Player player, ResourceEnum resource) {
    int capacity = player.getPowerPlants().stream()
            .filter(plant -> plant.getResourceEnums().contains(resource))
            .map(PowerPlant::getResources)
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
          ResourceEnum type,
          int amountToAdd) {

    IntStream.range(0, amountToAdd)
            .forEach(i ->
                    player.getResources().entrySet().stream()
                            .forEach(entry -> {
                              if (!entry.getKey().getResources().equals(entry.getValue().size())
                                      && entry.getKey().getResourceEnums().contains(type)) {
                                entry.getValue().add(type);
                              }
                            })
            );
  }

  public void addToPlayerCities(Player player, City city) {
    List<City> playersCities = player.getCities();
    playersCities.add(city);
  }

}