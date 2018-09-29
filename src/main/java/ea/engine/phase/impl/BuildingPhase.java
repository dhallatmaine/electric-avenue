package ea.engine.phase.impl;

import ea.data.City;
import ea.data.Player;
import ea.engine.GameState;
import ea.engine.State;
import ea.maps.BaseMap;
import ea.rules.BaseRules;
import ea.services.PlayerService;
import ea.views.CityView;
import ea.views.DefaultView;
import ea.views.PlayerView;

import java.util.*;

public class BuildingPhase extends BasePhaseImpl {

  private BaseMap map;
  private DefaultView defaultView;
  private CityView cityView;
  private PlayerView playerView;
  private PlayerService playerService;

  public BuildingPhase(GameState gameState, BaseMap map, PlayerService playerService) {
    super(gameState);
    this.map = map;
    this.playerService = playerService;
    defaultView = new DefaultView();
    cityView = new CityView();
    playerView = new PlayerView();
  }

  public void initiate(Integer gameId) {
    State game = getGameState().getById(gameId);
    List<Player> players = game.getPlayers();
    //gameState.setPhase(this);
    defaultView.outln("===== Building Phase =====");
    Iterator playerItr = players.iterator();
    while (playerItr.hasNext()) {
      Player p = (Player) playerItr.next();
      if (game.getRound() == 1) {
        firstRound(p);
      } else {
        build(p);
      }
    }
  }

  private void firstRound(Player p) {
    boolean done = false;
    while (!done) {
      cityView.displayCities(map.getCities());
      int choice = cityView.getCityToBuildInFromUser(map.getCities(), p);
      if (choice == 0) {
        return;
      }

      City c = map.getCities().get(choice - 1);

      if (p.getMoney() >= BaseRules.PHASE_1_CITY_COST && c.getPhase1() == null) {
        playerService.subtractMoneyFromPlayer(p, BaseRules.PHASE_1_CITY_COST);

        c.setPhase1(p);
        playerService.addToPlayerCities(p, c);
        done = true;
        if (!cityView.getConfirmDoneBuilding()) {
          defaultView.outln(p.getName() + " is done building.");
        } else {
          build(p);
        }
      } else {
        if (p.getMoney() < BaseRules.PHASE_1_CITY_COST) {
          defaultView.outln("ERROR: You do not have enough money to build here!");
        }
        if (c.getPhase1() != null) {
          defaultView.outln("ERROR: Someone has already built here!");
        }
        defaultView.outln("ERROR: You have no built anywhere!");
      }
    }
  }

  private void build(Player p) {
    List<City> cities;
    List<Integer> distances;

    boolean done = false;
    while (!done) {
      cities = new LinkedList<>();
      distances = new LinkedList<>();
      Iterator playerCityItr = p.getCities().iterator();
      int count = 1;
      playerView.displayPlayerAttributes(p);
      while (playerCityItr.hasNext()) {
        City c = (City) playerCityItr.next();
        Iterator cityItr = c.getConnectedCities().entrySet().iterator();

        defaultView.outln("From " + c.getName() + ", you have " + c.getConnectedCities().size() + " possible cities to connect to");

        while (cityItr.hasNext()) {
          Map.Entry pairs = (Map.Entry) cityItr.next();
          City city = (City) pairs.getKey();
          Integer distance = (Integer) pairs.getValue();

          if (city.getPhase1() == null) {
            defaultView.outln(count++ + ".) " + c.getName() + " to " + city.getName() + " has connection fee: " + distance);
            cities.add(city);
            distances.add(distance);
          }
        }
      }

      int choice = cityView.getCityToBuildInFromUser(cities, p);

      if (choice == 0) {
        return;
      }

      int total = distances.get(choice - 1) + BaseRules.PHASE_1_CITY_COST;

      if (p.getMoney() >= total) {
        playerService.subtractMoneyFromPlayer(p, total);
        City chosenCity = cities.get(choice - 1);
        chosenCity.setPhase1(p);
        playerService.addToPlayerCities(p, chosenCity);

        if (!cityView.getConfirmDoneBuilding()) {
          defaultView.outln(p.getName() + " is done building");
          done = true;
        }
      } else {
        defaultView.outln("You do not have enough money to build here.");
        defaultView.outln("You have no built anywhere!");
      }
    }
  }

}