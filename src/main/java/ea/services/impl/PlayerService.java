package ea.services.impl;

import ea.data.City;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;
import ea.rules.BaseRules;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class PlayerService {

  private List<Player> players;

  public void setupPlayers() {
    List<Player> playerList = new LinkedList<>();

    Player player1 = new Player();
    player1.setMoney(BaseRules.START_MONEY);
    player1.setName("Player 1");
    List<City> cities = new LinkedList<>();
    player1.setCities(cities);
    player1.setPowerPlants(new LinkedList<>());
    player1.setCoal(new LinkedList<>());
    player1.setOil(new LinkedList<>());
    player1.setTrash(new LinkedList<>());
    player1.setUranium(new LinkedList<>());
    playerList.add(player1);

    Player player2 = new Player();
    player2.setMoney(BaseRules.START_MONEY);
    player2.setName("Player 2");
    player2.setCities(new LinkedList<>());
    player2.setPowerPlants(new LinkedList<>());
    player2.setCoal(new LinkedList<>());
    player2.setOil(new LinkedList<>());
    player2.setTrash(new LinkedList<>());
    player2.setUranium(new LinkedList<>());
    playerList.add(player2);

    Player player3 = new Player();
    player3.setMoney(BaseRules.START_MONEY);
    player3.setName("Player 3");
    player3.setCities(new LinkedList<>());
    player3.setPowerPlants(new LinkedList<>());
    player3.setCoal(new LinkedList<>());
    player3.setOil(new LinkedList<>());
    player3.setTrash(new LinkedList<>());
    player3.setUranium(new LinkedList<>());
    playerList.add(player3);

    setPlayers(playerList);
  }

  public Player getPlayerByName(String name) {
    Iterator itr = getPlayers().iterator();
    while (itr.hasNext()) {
      Player player = (Player) itr.next();
      if (player.getName().equalsIgnoreCase(name)) {
        return player;
      }
    }

    return null;
  }

  public void subtractMoneyFromPlayer(Player player, int amount) {
    int money = player.getMoney();
    money = money - amount;
    player.setMoney(money);
  }

  public int getMaxResourcesAllowedForPurchase(Player player, int resource) {
    int resources = 0;
    if (resource == Resource.HYBRID) {
      resources += getMaxAllowedResources(player, Resource.COAL);
      resources += getMaxAllowedResources(player, Resource.OIL);
    } else {
      resources += getPlayerResourceListByConst(player, resource).size();
    }

    int playerResources = getMaxAllowedResources(player, resource);

    return playerResources - resources;
  }

  public void addToPlayerResources(Player player, int choice, int amount) {
    List<Resource> resources = getPlayerResourceListByConst(player, choice);
    while (amount > 0) {
      resources.add(new Resource(choice, 0));
      amount--;
    }
  }

  public void removeFromPlayerResources(Player player, List<Resource> resources, int amount) {
    if (resources.size() >= amount) {
      Iterator itr = resources.iterator();
      while (itr.hasNext() && amount > 0) {
        itr.remove();
        amount--;
      }
    }
  }

  public void addToPlayerCities(Player player, City city) {
    List<City> playersCities = player.getCities();
    playersCities.add(city);
  }

  private List<Resource> getPlayerResourceListByConst(Player player, int resource) {
    switch (resource) {
      case 1:
        return player.getCoal();
      case 2:
        return player.getOil();
      case 3:
        return player.getTrash();
      case 4:
        return player.getUranium();
      default:
        return null;
    }
  }

  private Integer getMaxAllowedResources(Player player, int resource) {
    List<PowerPlant> plants = player.getPowerPlants();

    int resources = 0;
    Iterator itr = plants.iterator();
    while (itr.hasNext()) {
      PowerPlant plant = (PowerPlant) itr.next();

      if ((plant.getType() == resource) || (plant.getType() == Resource.HYBRID && (resource == Resource.COAL || resource == Resource.OIL))) {
        resources += plant.getResources();
      }
    }

    return resources;
  }

  public List<Player> getPlayers() { return players; }
  public void setPlayers(List<Player> players) { this.players = players; }

}