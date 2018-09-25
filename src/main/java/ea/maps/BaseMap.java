package ea.maps;

import ea.data.City;

import java.util.List;

public class BaseMap {

  private List<City> cities;

  public static final String REGION_1 = "GREEN";
  public static final String REGION_2 = "RED";
  public static final String REGION_3 = "BLUE";
  public static final String REGION_4 = "PURPLE";
  public static final String REGION_5 = "YELLOW";
  public static final String REGION_6 = "BROWN";

  public List<City> getCities() { return cities; }
  public void setCities(List<City> cities) { this.cities = cities; }

}