package ea.maps;

import com.google.common.collect.ImmutableList;
import ea.data.City;
import ea.data.Resource;
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

  public static Map<Resource, List<Integer>> initializeResources() {
    Map<Resource, List<Integer>> built = new HashMap<>();

    built.put(Resource.COAL, ImmutableList.of(
            1, 1, 1,
            2, 2, 2,
            3, 3, 3,
            4, 4, 4,
            5, 5, 5,
            6, 6, 6,
            7, 7, 7,
            8, 8, 8));

    built.put(Resource.OIL, ImmutableList.of(
            0, 0, 0,
            0, 0, 0,
            3, 3, 3,
            4, 4, 4,
            5, 5, 5,
            6, 6, 6,
            7, 7, 7,
            8, 8, 8));

    built.put(Resource.TRASH, ImmutableList.of(
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            7, 7, 7,
            8, 8, 8));

    built.put(Resource.URANIUM, ImmutableList.of(
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            14,
            16));

    return built;
  }

}