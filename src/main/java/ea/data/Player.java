package ea.data;

import java.util.List;

public class Player {

  private String name;
  private Integer money;
  private List<PowerPlant> powerPlants;
  private List<City> cities;
  private List<Resource> coal;
  private List<Resource> oil;
  private List<Resource> trash;
  private List<Resource> uranium;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Integer getMoney() { return money; }
  public void setMoney(Integer money) { this.money = money; }

  public List<PowerPlant> getPowerPlants() { return powerPlants; }
  public void setPowerPlants(List<PowerPlant> powerPlants) { this.powerPlants = powerPlants; }

  public List<City> getCities() { return cities; }
  public void setCities(List<City> cities) { this.cities = cities; }

  public List<Resource> getCoal() { return coal; }
  public void setCoal(List<Resource> coal) { this.coal = coal; }

  public List<Resource> getOil() { return oil; }
  public void setOil(List<Resource> oil) { this.oil = oil; }

  public List<Resource> getTrash() { return trash; }
  public void setTrash(List<Resource> trash) { this.trash = trash; }

  public List<Resource> getUranium() { return uranium; }
  public void setUranium(List<Resource> uranium) { this.uranium = uranium; }

}