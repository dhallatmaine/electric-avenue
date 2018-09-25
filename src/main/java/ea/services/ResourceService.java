package ea.services;

import ea.data.Resource;

import java.util.List;

public interface ResourceService {

  List<Resource> getCoal();
  void setCoal(List<Resource> coal);
  List<Resource> getOil();
  void setOil(List<Resource> oil);
  List<Resource> getTrash();
  void setTrash(List<Resource> trash);
  List<Resource> getUranium();
  void setUranium(List<Resource> uranium);
  void initializeResources();
  List<Resource> getResourceListByConst(int choice);
  String getResourceNameByConst(int choice);
  Integer getPrice(List<Resource> resources, int amount);
  void removeFromMarket(List<Resource> resources, int amount);

}