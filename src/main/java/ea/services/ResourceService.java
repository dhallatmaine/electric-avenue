package ea.services;

import ea.engine.GameState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  public Integer getPrice(List<Integer> resources, int amount) {
    return resources.stream()
            .filter(i -> i > 0)
            .limit(amount)
            .mapToInt(Integer::intValue)
            .sum();
  }

  public List<Integer> removeFromMarket(List<Integer> resources, int amount) {
    Stream<Integer> emptySpaces = resources.stream()
            .filter(i -> i == 0);

    Stream<Integer> removed = Stream.iterate(0, i -> 0)
            .limit(amount);

    Stream<Integer> newEmptyArea = Stream.concat(emptySpaces, removed);

    Stream<Integer> remaining = resources.stream()
            .filter(i -> i > 0)
            .skip(amount);

    return Stream.concat(newEmptyArea, remaining).collect(Collectors.toList());
  }

}