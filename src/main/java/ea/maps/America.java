package ea.maps;

import com.google.common.collect.ImmutableList;
import ea.data.Resource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class America {

    private final static List<City> americaMap =
        MapFactory.getCityListFromJson("america");

    public static Map<String, City> getAmericaMap() {
        return americaMap.stream()
                .collect(Collectors.toMap(City::getName, Function.identity()));
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
