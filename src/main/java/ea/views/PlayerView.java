package ea.views;

import ea.data.City;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.data.Resource;

import java.util.Iterator;
import java.util.List;

public class PlayerView {

  private DefaultView defaultView;

  public PlayerView() {
    defaultView = new DefaultView();
  }

  public void displayPlayersAttributes(List<Player> players) {
    Iterator itr = players.iterator();
    while (itr.hasNext()) {
      Player p = (Player) itr.next();
      displayPlayerAttributes(p);
    }
  }

  public void displayPlayerAttributes(Player player) {
    defaultView.outln("------ Player ------");
    defaultView.outln("Name: \"" + player.getName() + "\"");
    defaultView.outln("Money: " + player.getMoney() + " elektro");

    defaultView.outln("Cities: " + player.getCities().size());
    Iterator itr = player.getCities().iterator();
    while (itr.hasNext()) {
      City city = (City) itr.next();
      defaultView.outln("City: " + city.getName());
    }

    defaultView.outln("Power Plants: " + player.getPowerPlants().size());
    itr = player.getPowerPlants().iterator();
    while (itr.hasNext()) {
      PowerPlant powerPlant = (PowerPlant) itr.next();
      defaultView.outln("Card - value: " + powerPlant.getValue());
    }

    defaultView.outln("Resources: ");
    defaultView.out("[Coal: " + player.getCoal().size() + "] ");
    defaultView.out("[Oil: " + player.getOil().size() + "] ");
    defaultView.out("[Trash: " + player.getTrash().size() + "] ");
    defaultView.out("[Uranium: " + player.getUranium().size() + "]\n");
  }

  public void displayPlayersCities(List<Player> players) {
    defaultView.outln("----- Player Stats ------");
    Iterator itr = players.iterator();
    while (itr.hasNext()) {
      Player p = (Player) itr.next();
      defaultView.out("Player: \"" + p.getName() + "\" Cities: " + p.getCities().size() + " ");
      defaultView.out("[Coal: " + p.getCoal().size() + "] ");
      defaultView.out("[Oil: " + p.getOil().size() + "] ");
      defaultView.out("[Trash: " + p.getTrash().size() + "] ");
      defaultView.outln("[Uranium: " + p.getUranium().size() + "]");
    }
  }

}