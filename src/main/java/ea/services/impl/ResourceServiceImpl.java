package ea.services.impl;

import ea.data.Resource;
import ea.services.ResourceService;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

  private List<Resource> coal;
  private List<Resource> oil;
  private List<Resource> trash;
  private List<Resource> uranium;

  public List<Resource> getCoal() { return coal; }
  public void setCoal(List<Resource> coal) { this.coal = coal; }

  public List<Resource> getOil() { return oil; }
  public void setOil(List<Resource> oil) { this.oil = oil; }

  public List<Resource> getTrash() { return trash; }
  public void setTrash(List<Resource> trash) { this.trash = trash; }

  public List<Resource> getUranium() { return uranium; }
  public void setUranium(List<Resource> uranium) { this.uranium = uranium; }

  public void initializeResources() {
    Resource resource = new Resource();

    // Start at 1 for coal
    List<Resource> coalList = new LinkedList<Resource>();
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        coalList.add(new Resource(Resource.COAL, i));
      }
    }
    setCoal(coalList);

    // 3 for oil
    List<Resource> oilList = new LinkedList<Resource>();
    for (int i = 3; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        oilList.add(new Resource(Resource.OIL, i));
      }
    }
    setOil(oilList);

    // 7 for trash
    List<Resource> trashList = new LinkedList<Resource>();
    for (int i = 7; i <= 8; i++) {
      for (int j = 1; j <= 3; j++) {
        trashList.add(new Resource(Resource.TRASH, i));
      }
    }
    setTrash(trashList);

    // 14 for uranium
    List<Resource> uraniumList = new LinkedList<Resource>();
    uraniumList.add(new Resource(Resource.URANIUM, 14));
    uraniumList.add(new Resource(Resource.URANIUM, 16));
    setUranium(uraniumList);
  }

  public List<Resource> getResourceListByConst(int choice) {
    switch (choice) {
      case 1:
        return getCoal();
      case 2:
        return getOil();
      case 3:
        return getTrash();
      case 4:
        return getUranium();
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
    List<Resource> removeList = new LinkedList<Resource>();

    Iterator itr = resources.iterator();
    while (itr.hasNext() && amount > 0) {
      Resource r = (Resource) itr.next();
      removeList.add(r);
      amount--;
    }

    resources.removeAll(removeList);
  }

}