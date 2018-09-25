package ea.services;

import ea.data.Resource;
import ea.engine.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class ResourceService {

  private final GameState gameState;

  @Autowired
  public ResourceService(GameState gameState) {
    this.gameState = gameState;
  }

  public void initializeResources() {
    // Start at 1 for coal
    List<Resource> coalList = new LinkedList<>();
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        coalList.add(new Resource(Resource.COAL, i));
      }
    }
    gameState.setCoal(coalList);

    // 3 for oil
    List<Resource> oilList = new LinkedList<>();
    for (int i = 3; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        oilList.add(new Resource(Resource.OIL, i));
      }
    }
    gameState.setOil(oilList);

    // 7 for trash
    List<Resource> trashList = new LinkedList<>();
    for (int i = 7; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        trashList.add(new Resource(Resource.TRASH, i));
      }
    }
    gameState.setTrash(trashList);

    // 14 for uranium
    List<Resource> uraniumList = new LinkedList<>();
    uraniumList.add(new Resource(Resource.URANIUM, 14));
    uraniumList.add(new Resource(Resource.URANIUM, 16));
    gameState.setUranium(uraniumList);
  }

  public List<Resource> getResourceListByConst(int choice) {
    switch (choice) {
      case 1:
        return gameState.getCoal();
      case 2:
        return gameState.getOil();
      case 3:
        return gameState.getTrash();
      case 4:
        return gameState.getUranium();
      default:
        return null;
    }
  }

  public String getResourceNameByConst(int choice) {
    switch (choice) {
      case 1:
        return "Coal";
      case 2:
        return "Oil";
      case 3:
        return "Trash";
      case 4:
        return "Uranium";
      default:
        return "";
    }
  }

  public Integer getPrice(List<Resource> resources, int amount) {
    int price = 0;

    Iterator itr = resources.iterator();
    while (itr.hasNext() && amount > 0) {
      Resource r = (Resource) itr.next();
      price += r.getPrice();
      amount--;
    }

    return price;
  }

  public void removeFromMarket(List<Resource> resources, int amount) {
    List<Resource> removeList = new LinkedList<>();

    Iterator itr = resources.iterator();
    while (itr.hasNext() && amount > 0) {
      Resource r = (Resource) itr.next();
      removeList.add(r);
      amount--;
    }

    resources.removeAll(removeList);
  }

}