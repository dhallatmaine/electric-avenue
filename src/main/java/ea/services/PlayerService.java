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
    player1.setId(1L);
    player1.setColor(Color.BLACK);
    player1.setMoney(BaseRules.START_MONEY);
    player1.setName("Player 1");
    player1.setCities(new LinkedList<>());
    player1.setPowerPlants(new LinkedList<>());
    player1.setResources(new HashMap<>());
    playerList.add(player1);

    Player player2 = new Player();
    player2.setId(2L);
    player2.setColor(Color.BLUE);
    player2.setMoney(BaseRules.START_MONEY);
    player2.setName("Player 2");
    player2.setCities(new LinkedList<>());
    player2.setPowerPlants(new LinkedList<>());
    player2.setResources(new HashMap<>());
    playerList.add(player2);

    Player player3 = new Player();
    player3.setId(3L);
    player3.setColor(Color.GREEN);
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
                              if (!entry.getKey().getResourceCapacity().equals(entry.getValue().size())
                                      && entry.getKey().getResources().contains(type)) {
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