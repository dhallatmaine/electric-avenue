package ea.maps;

import com.google.common.collect.ImmutableList;
import ea.data.City;
import ea.data.ResourceEnum;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class America extends BaseMap {

  public void initializeCities() {
    City miami = new City()
            .withName("Miami")
            .withRegion(BaseMap.REGION_1);

    City tampa = new City()
            .withName("Tampa")
            .withRegion(BaseMap.REGION_1);
    City jacksonville = new City()
            .withName("Jacksonville")
            .withRegion(BaseMap.REGION_1);

    City savannah = new City()
            .withName("Savannah")
            .withRegion(BaseMap.REGION_1);

    City atlanta = new City()
            .withName("Atlanta")
            .withRegion(BaseMap.REGION_1);

    City raleigh = new City()
            .withName("Raleigh")
            .withRegion(BaseMap.REGION_1);

    City birmingham = new City()
            .withName("Birmingham")
            .withRegion(BaseMap.REGION_2);

    City knoxville = new City()
            .withName("Knoxville")
            .withRegion(BaseMap.REGION_5);

    // Miami
    Map<City, Integer> connectedCities = new HashMap<>();
    connectedCities.put(tampa, 4);
    miami.withConnectedCities(connectedCities);

    // Tampa
    connectedCities = new HashMap<>();
    connectedCities.put(miami, 4);
    connectedCities.put(jacksonville, 4);
    tampa.withConnectedCities(connectedCities);

    // Jacksonville
    connectedCities = new HashMap<>();
    connectedCities.put(tampa, 4);
    connectedCities.put(savannah, 0);
    jacksonville.withConnectedCities(connectedCities);

    // Savannah
    connectedCities = new HashMap<>();
    connectedCities.put(atlanta, 7);
    connectedCities.put(jacksonville, 0);
    savannah.withConnectedCities(connectedCities);

    // Atlanta
    connectedCities = new HashMap<>();
    connectedCities.put(savannah, 7);
    connectedCities.put(birmingham, 3);
    connectedCities.put(raleigh, 7);
    connectedCities.put(knoxville, 3);
    atlanta.withConnectedCities(connectedCities);

    // Raleigh
    connectedCities = new HashMap<>();
    connectedCities.put(savannah, 7);
    connectedCities.put(atlanta, 7);
    raleigh.withConnectedCities(connectedCities);

    // Birmingham
    connectedCities = new HashMap<>();
    connectedCities.put(atlanta, 3);
    birmingham.withConnectedCities(connectedCities);

    // Knoxville
    connectedCities = new HashMap<>();
    connectedCities.put(atlanta, 3);
    knoxville.withConnectedCities(connectedCities);

    setCities(Arrays.asList(miami, tampa, jacksonville, savannah, atlanta, raleigh, birmingham, knoxville));
  }

  public static Map<ResourceEnum, List<OptionalInt>> initializeResources() {
    Map<ResourceEnum, List<OptionalInt>> built = new HashMap<>();

    built.put(ResourceEnum.COAL, ImmutableList.of(
            OptionalInt.of(1), OptionalInt.of(1), OptionalInt.of(1),
            OptionalInt.of(2), OptionalInt.of(2), OptionalInt.of(2),
            OptionalInt.of(3), OptionalInt.of(3), OptionalInt.of(3),
            OptionalInt.of(4), OptionalInt.of(4), OptionalInt.of(4),
            OptionalInt.of(5), OptionalInt.of(5), OptionalInt.of(5),
            OptionalInt.of(6), OptionalInt.of(6), OptionalInt.of(6),
            OptionalInt.of(7), OptionalInt.of(7), OptionalInt.of(7),
            OptionalInt.of(8), OptionalInt.of(8), OptionalInt.of(8)));

    built.put(ResourceEnum.OIL, ImmutableList.of(
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.of(3), OptionalInt.of(3), OptionalInt.of(3),
            OptionalInt.of(4), OptionalInt.of(4), OptionalInt.of(4),
            OptionalInt.of(5), OptionalInt.of(5), OptionalInt.of(5),
            OptionalInt.of(6), OptionalInt.of(6), OptionalInt.of(6),
            OptionalInt.of(7), OptionalInt.of(7), OptionalInt.of(7),
            OptionalInt.of(8), OptionalInt.of(8), OptionalInt.of(8)));

    built.put(ResourceEnum.TRASH, ImmutableList.of(
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.empty(), OptionalInt.empty(), OptionalInt.empty(),
            OptionalInt.of(7), OptionalInt.of(7), OptionalInt.of(7),
            OptionalInt.of(8), OptionalInt.of(8), OptionalInt.of(8)));

    built.put(ResourceEnum.URANIUM, ImmutableList.of(
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.empty(),
            OptionalInt.of(14),
            OptionalInt.of(16)));

    return built;
  }

}