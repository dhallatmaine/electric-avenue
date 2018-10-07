package ea.services;

import ea.data.Resource;
import ea.engine.GameState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Component
public class ResourceService {

  private final GameState gameState;

  @Autowired
  public ResourceService(GameState gameState) {
    this.gameState = gameState;
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

  public Integer getPrice(List<OptionalInt> resources, int amount) {
    return resources.stream()
            .filter(OptionalInt::isPresent)
            .limit(amount)
            .mapToInt(OptionalInt::getAsInt)
            .sum();
  }

  public List<OptionalInt> removeFromMarketEnum(List<OptionalInt> resources, int amount) {
    return resources.stream()
            .filter(OptionalInt::isPresent)
            .limit(amount)
            .collect(Collectors.toList());
  }

}